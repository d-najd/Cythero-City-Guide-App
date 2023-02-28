package com.cythero.data.attraction

import com.cythero.data.utils.Urls
import com.cythero.data.utils.processRequest
import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionHolder
import com.cythero.domain.attraction.service.AttractionRepository
import com.cythero.domain.city.model.CityHolder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

object RemoteAttractionRepository : AttractionRepository {
	private val factory: AttractionRepositoryApi =
		Injekt.get<Retrofit.Builder>()
			.baseUrl("${Urls.ATTRACTION.getAppendedUrl()}/").build().create(AttractionRepositoryApi::class.java)

	override suspend fun getMulti(page: Int, size: Int): AttractionHolder? = factory.getByPage(
		page = page,
		size = size,
	).processRequest()

	override suspend fun getOne(id: Long): Attraction? = factory.getById(id).processRequest()

}

private interface AttractionRepositoryApi {

	@GET("testing/getAll")
	fun getAll(): Call<AttractionHolder>

	@GET("page/{page}")
	fun getByPage(
		@Path("page") page: Int,
		@Query("pageSize") size: Int = 10,
	): Call<AttractionHolder>

	@GET("{id}")
	fun getById(
		@Path("id") id: Long,
	): Call<Attraction>

}
