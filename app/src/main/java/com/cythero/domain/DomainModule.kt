package com.cythero.domain

import com.google.gson.GsonBuilder
import com.cythero.data.utils.Urls
import com.cythero.cityguideapp.ui.base.event.EventSendableHolder
import com.cythero.data.city.FakeCityRepository
import com.cythero.data.city.RemoteCityRepository
import com.cythero.domain.city.interactor.GetCity
import com.cythero.domain.city.service.CityRepository
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

        when (USE_FAKES) {
            true -> {
                addSingletonFactory<CityRepository> { FakeCityRepository }
            }
            false -> {
                addSingletonFactory<CityRepository> { RemoteCityRepository }
            }
        }

        addFactory { GetCity(get()) }
    }
}
