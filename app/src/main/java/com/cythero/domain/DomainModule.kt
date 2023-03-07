package com.cythero.domain

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.cythero.data.attraction.FakeAttractionRepository
import com.cythero.data.attraction.RemoteAttractionRepository
import com.google.gson.GsonBuilder
import com.cythero.data.utils.Urls
import com.cythero.data.city.FakeCityRepository
import com.cythero.data.city.RemoteCityRepository
import com.cythero.data.image_url.FakeImageUrlRepository
import com.cythero.data.image_url.RemoteImageUrlRepository
import com.cythero.data.utils.AuthConfig
import com.cythero.domain.attraction.interactor.GetAttraction
import com.cythero.domain.attraction.service.AttractionRepository
import com.cythero.domain.city.interactor.GetCity
import com.cythero.domain.city.service.CityRepository
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.domain.image_url.service.ImageUrlRepository
import com.cythero.util.DateFormat
import com.github.javafaker.Faker
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {
    companion object {
        private const val USE_FAKES = false
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

        // added this for clarity
        @Suppress("RemoveExplicitTypeArguments")
        addSingletonFactory<RequestManager> {
            Glide.with(Injekt.get<Context>().applicationContext)
        }

        addSingletonFactory {
            Faker()
        }

        addSingletonFactory {  }

        addSingletonFactory {
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                //.requestIdToken(AuthConfig.serverId)
                //.requestServerAuthCode(AuthConfig.serverId)
        }

        when (USE_FAKES) {
            true -> {
                addSingletonFactory<CityRepository> { FakeCityRepository }
                addSingletonFactory<ImageUrlRepository>() { FakeImageUrlRepository }
                addSingletonFactory<AttractionRepository> { FakeAttractionRepository }
            }
            false -> {
                addSingletonFactory<CityRepository> { RemoteCityRepository }
                addSingletonFactory<ImageUrlRepository>() { RemoteImageUrlRepository }
                addSingletonFactory<AttractionRepository> { RemoteAttractionRepository }
            }
        }

        addFactory { GetCity(get()) }

        addFactory { GetImageByUrl(get()) }

        addFactory { GetAttraction(get()) }
    }
}
