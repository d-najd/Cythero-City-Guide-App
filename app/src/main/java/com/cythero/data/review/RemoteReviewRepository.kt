package com.cythero.data.review

import com.cythero.data.utils.Urls
import com.cythero.data.utils.processRequest
import com.cythero.domain.review.model.ReviewHolder
import com.cythero.domain.review.service.ReviewRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

object RemoteReviewRepository : ReviewRepository {
	private val factory: AttractionRepositoryApi =
		Injekt.get<Retrofit.Builder>()
			.baseUrl("${Urls.ATTRACTION.getAppendedUrl()}/").build()
			.create(AttractionRepositoryApi::class.java)

	override suspend fun getMulti(page: Long, size: Long): ReviewHolder? = factory.getByPage(
		page = page,
		size = size,
	).processRequest()

}

private interface AttractionRepositoryApi {

	@GET("page/{page}")
	fun getByPage(
		@Path("page") page: Long,
		@Query("pageSize") size: Long,
	): Call<ReviewHolder>

}
