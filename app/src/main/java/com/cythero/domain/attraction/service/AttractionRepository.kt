package com.cythero.domain.attraction.service

import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.city.model.CityHolder

interface AttractionRepository {

	suspend fun getOne(id: Long): Attraction?

}
