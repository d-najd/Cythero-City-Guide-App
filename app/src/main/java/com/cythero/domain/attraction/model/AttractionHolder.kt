package com.cythero.domain.attraction.model

import com.cythero.getFaker
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class AttractionHolder(
	@SerializedName("data") val `data`: List<Attraction> = emptyList()
) {
	companion object {
		fun fake(generateCount: Int = 10): AttractionHolder {
			val returnList = mutableListOf<Attraction>()
			for(i in 1..generateCount) {
				returnList.add(
					Attraction(
						id = i.toLong(),
						cityId = Random.nextLong(),
						locationId = Random.nextLong(),
						location = Location.fake(),
						name = getFaker().company().name(),
						description = getFaker().lorem().paragraph(Random.nextInt(1, 50)),
					)
				)
			}
			return AttractionHolder(returnList)
		}
	}
}
