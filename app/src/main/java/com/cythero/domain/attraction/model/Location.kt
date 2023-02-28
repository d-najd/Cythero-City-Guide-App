package com.cythero.domain.attraction.model


import android.graphics.drawable.Drawable
import com.cythero.getFaker
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class Location(
    @SerializedName("id") val id: Long,
    @SerializedName("flagPath") val flagPath: String,
    @SerializedName("address") val address: String,
    @Transient val flagPathDrawable: Drawable? = null,
) {
    companion object {
        fun fake(): Location = Location(
            id = Random.nextLong(),
            flagPath = "https://picsum.photos/1080/720",
            address = getFaker().address().fullAddress(),
        )
    }
}
