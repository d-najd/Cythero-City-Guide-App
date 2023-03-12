package com.cythero.data.city

import com.cythero.data.utils.Urls
import com.cythero.data.utils.processRequest
import com.cythero.domain.city.model.CityHolder
import com.cythero.domain.city.service.CityRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

object RemoteCityRepository : CityRepository {
    private val factory: ProjectTableRepositoryApi =
        Injekt.get<Retrofit.Builder>()
            .baseUrl("${Urls.CITY.getAppendedUrl()}/").build()
            .create(ProjectTableRepositoryApi::class.java)

    override suspend fun getAll(): CityHolder? = factory.getAll().processRequest()
}

private interface ProjectTableRepositoryApi {
    @GET("testing/getAll")
    fun getAll(): Call<CityHolder>
}