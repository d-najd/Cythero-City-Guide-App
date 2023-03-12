package com.cythero.domain.city.service

import com.cythero.domain.city.model.CityHolder

interface CityRepository {

	@Deprecated("replace with pager")
	suspend fun getAll(): CityHolder?

}