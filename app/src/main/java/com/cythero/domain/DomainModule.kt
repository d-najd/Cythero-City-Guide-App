package com.cythero.domain

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.cythero.cityguideapp.MainActivity
import com.google.gson.GsonBuilder
import com.cythero.data.utils.Urls
import com.cythero.cityguideapp.ui.base.event.EventSendableHolder
import com.cythero.data.city.FakeCityRepository
import com.cythero.data.city.RemoteCityRepository
import com.cythero.data.image_url.ImageUrlRepositoryImpl
import com.cythero.domain.city.interactor.GetCity
import com.cythero.domain.city.service.CityRepository
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.domain.image_url.service.ImageUrlRepository
import com.cythero.util.DateFormat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {
    companion object {
        private const val USE_FAKES = true
    }
    
    override fun InjektRegistrar.registerInjectables() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        addSingletonFactory {
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
        
        addSingletonFactory {
            GsonBuilder()
                .setDateFormat(DateFormat.defaultRequestDateFormat().toPattern())
                .create()
        }

        addSingletonFactory {
            Retrofit.Builder()
                .baseUrl(Urls.API.baseUrlLocal)
                .addConverterFactory(GsonConverterFactory.create(Injekt.get()))
                .client(Injekt.get())
        }

        addSingletonFactory {
            Injekt.get<Retrofit.Builder>()
                .build()
        }

        addSingletonFactory<RequestManager> {
            Glide.with(Injekt.get<Context>().applicationContext)
        }

        when (USE_FAKES) {
            true -> {
                addSingletonFactory<CityRepository> { FakeCityRepository }

                addFactory<ImageUrlRepository> { ImageUrlRepositoryImpl }
            }
            false -> {
                addSingletonFactory<CityRepository> { RemoteCityRepository }

                addFactory<ImageUrlRepository> { ImageUrlRepositoryImpl }
            }
        }

        addFactory { GetCity(get()) }

        addFactory { GetImageByUrl(get()) }
    }
}
