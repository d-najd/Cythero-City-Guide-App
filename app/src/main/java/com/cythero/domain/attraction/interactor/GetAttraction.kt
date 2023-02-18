package com.cythero.domain.attraction.interactor

import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.service.AttractionRepository

class GetAttraction(
	private val repository: AttractionRepository,
) {

	suspend fun awaitAll(): List<Attraction> = repository.getAll()?.data ?: emptyList()

	suspend fun awaitOne(id: Long): Attraction? = repository.getOne(id)

}
