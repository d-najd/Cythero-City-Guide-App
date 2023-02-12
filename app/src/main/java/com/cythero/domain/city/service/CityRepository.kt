package com.cythero.domain.city.service

import com.cythero.domain.city.model.CityHolder

interface CityRepository {

	suspend fun getAll(): CityHolder?

}