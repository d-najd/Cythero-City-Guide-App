package com.cythero.domain.attraction.model

import com.cythero.getFaker
import com.google.gson.annotations.SerializedName
import kotlin.random.Random
import kotlin.random.nextLong

data class AttractionHolder(
	@SerializedName("data") val `data`: List<Attraction> = emptyList()
) {
	companion object {
		fun fake(generateCount: Long = 10, generateImageCount: Long? = null): AttractionHolder {
			val returnList = mutableListOf<Attraction>()
			val images = mutableListOf<CityGuideImage>()
			val specificGenerateImageCount = generateImageCount ?: Random.nextLong(from = 1, until = 5)
			for(i in 0..specificGenerateImageCount) {
				images.add(CityGuideImage.fake())
			}
			for(i in 1..generateCount) {
				returnList.add(
					Attraction(
						id = i,
						cityId = Random.nextLong(),
						locationId = Random.nextLong(),
						location = Location.fake(),
						name = getFaker().company().name(),
						description = getFaker().lorem().paragraph(Random.nextInt(1, 50)),
						images = images
					)
				)
			}
			return AttractionHolder(returnList)
		}
	}
}
