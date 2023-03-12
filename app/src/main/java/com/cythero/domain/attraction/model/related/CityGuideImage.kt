package com.cythero.domain.attraction.model.related

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class CityGuideImage(
    @SerializedName("id") val id: Long,
    @SerializedName("path") val path: String,
    @Transient val pathDrawable: Drawable? = null,
    @SerializedName("attractionId") val attractionId: Long? = null,
    @SerializedName("cityId") val cityId: Long? = null,
    @SerializedName("reviewId") val reviewId: Long? = null,
) {
    companion object {
        fun fake(): CityGuideImage = CityGuideImage(
            id = Random.nextLong(),
            path = "https://picsum.photos/1080/720",
            attractionId = Random.nextLong(),
            cityId = Random.nextLong(),
            reviewId = Random.nextLong(),
        )
    }
}