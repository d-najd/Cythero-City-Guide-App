package com.cythero.domain.city.service

import com.cythero.domain.city.model.CityHolder

interface CityRepository {

	/**
	 * TODO get cities in parts instead of the whole db
	 */
	suspend fun getAll(): CityHolder?

}