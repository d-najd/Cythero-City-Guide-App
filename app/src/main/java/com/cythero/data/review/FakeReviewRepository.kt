package com.cythero.data.review

import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionHolder
import com.cythero.domain.attraction.service.AttractionRepository
import com.cythero.domain.review.model.ReviewHolder
import com.cythero.domain.review.service.ReviewRepository

@Suppress("RedundantNullableReturnType")
object FakeReviewRepository : ReviewRepository {

	override suspend fun getMulti(page: Long, size: Long): ReviewHolder? = ReviewHolder.fake(size)

}
