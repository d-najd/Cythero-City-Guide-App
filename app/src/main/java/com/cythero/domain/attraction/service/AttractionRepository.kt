package com.cythero.domain.attraction.service

import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionHolder

interface AttractionRepository {

	suspend fun getMulti(page: Long, size: Long): AttractionHolder?

	suspend fun getOne(id: Long): Attraction?

}
