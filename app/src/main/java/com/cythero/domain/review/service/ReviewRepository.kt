package com.cythero.domain.review.service

import com.cythero.domain.review.model.ReviewHolder

interface ReviewRepository {

	suspend fun getMulti(page: Long, size: Long): ReviewHolder?

}