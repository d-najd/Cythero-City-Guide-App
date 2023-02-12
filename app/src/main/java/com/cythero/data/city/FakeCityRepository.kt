package com.cythero.data.city

import com.cythero.domain.city.model.City
import com.cythero.domain.city.model.CityHolder
import com.cythero.domain.city.service.CityRepository

@Suppress("RedundantNullableReturnType")
object FakeCityRepository : CityRepository {

	override suspend fun getAll(): CityHolder? = CityHolder.mock()

}

