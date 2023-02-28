package com.cythero.domain.city.model

import com.cythero.domain.image_url.model.Image
import com.cythero.getFaker
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class CityHolder(
	@SerializedName("data") val `data`: List<City> = emptyList()
) {
	companion object {
		fun fake(generateCount: Int = 10): CityHolder {
			val returnList = mutableListOf<City>()
			for(i in 1..generateCount) {
				val mockImageCount = Random.nextInt(1, 10)
				val imageList = mutableListOf<Image>()
				for(b in 1..mockImageCount) {
					imageList.add(Image.fake())
				}
				returnList.add(
					City(
						id = i.toLong(),
						countryId = Random.nextLong(),
						locationId = Random.nextLong(),
						name = getFaker().country().capital(),
						images = imageList,
					)
				)
			}
			return CityHolder(returnList)
		}
	}
}
