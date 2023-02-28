package com.cythero.domain.attraction.model


import com.google.gson.annotations.SerializedName

data class Attraction(
    @SerializedName("id") val id: Long,
    @SerializedName("cityId") val cityId: Long,
    @SerializedName("locationId") val locationId: Long,
    @SerializedName("location") val location: Location,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
) {
    companion object {
        fun fake(): Attraction = AttractionHolder.fake(1).data[0]
    }
}