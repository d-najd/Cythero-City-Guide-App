package com.cythero.domain.image_url.model


import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

/**
 * @param drawable this value should be populated by the app
 */
data class Image(
    @SerializedName("id") val id: Long,
    @SerializedName("path") val path: String,
    @SerializedName("cityId") val cityId: Long? = null,
    @Transient val drawable: Drawable? = null,
) {
    companion object {
        fun fake(): Image = Image(
            id = Random.nextLong(),
            path = "https://picsum.photos/1080/720",
            cityId = Random.nextLong(),
        )
    }
}
