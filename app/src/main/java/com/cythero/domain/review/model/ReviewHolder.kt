package com.cythero.domain.review.model

import com.cythero.getFaker
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class ReviewHolder(
	@SerializedName("data") val `data`: List<Review> = emptyList()
) {
	companion object {
		fun fake(generateCount: Long = 10): ReviewHolder {
			val returnList = mutableListOf<Review>()
			for(i in 1..generateCount) {
				returnList.add(
					Review(
						id = i,
						attractionId = i,
						stars = Random.nextInt(),
						title = getFaker().lorem().sentence(Random.nextInt(1,3)),
						description = if(Random.nextBoolean()) getFaker().lorem().paragraph(
							Random.nextInt(5, 50)
						) else null,
						username = getFaker().name().username(),
					)
				)
			}
			return ReviewHolder(returnList)
		}
	}
}
