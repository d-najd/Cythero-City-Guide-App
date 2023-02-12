package com.cythero.domain.city.interactor

import com.cythero.domain.city.model.City
import com.cythero.domain.city.service.CityRepository

class GetCity(
	private val repository: CityRepository,
) {
	suspend fun awaitAll(): List<City> = repository.getAll()?.data ?: emptyList()
}
