package com.cythero.domain.review.interactor

import com.cythero.domain.review.model.Review
import com.cythero.domain.review.service.ReviewRepository

class GetReview(
	private val repository: ReviewRepository,
) {

	suspend fun awaitMulti(page: Long, size: Long = 10): List<Review> = repository.getMulti(
		page = page,
		size = size,
	)?.data ?: emptyList()

}
