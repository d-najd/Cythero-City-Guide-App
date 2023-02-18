package com.cythero.data.attraction

import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionHolder
import com.cythero.domain.attraction.service.AttractionRepository

@Suppress("RedundantNullableReturnType")
object FakeAttractionRepository : AttractionRepository {

	override suspend fun getAll(): AttractionHolder? = AttractionHolder.mock()

	override suspend fun getOne(id: Long): Attraction? = Attraction.mock()

}
