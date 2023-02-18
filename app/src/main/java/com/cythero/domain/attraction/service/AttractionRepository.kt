package com.cythero.domain.attraction.service

import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionHolder
import com.cythero.domain.city.model.CityHolder

interface AttractionRepository {
	/**
	 * TODO get cities in parts instead of the whole db
	 */
	suspend fun getAll(): AttractionHolder?

	suspend fun getOne(id: Long): Attraction?

}
