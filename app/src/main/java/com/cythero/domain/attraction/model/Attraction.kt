package com.cythero.domain.attraction.model


import com.cythero.domain.attraction.model.related.CityGuideImage
import com.cythero.domain.attraction.model.related.Location
import com.google.gson.annotations.SerializedName

data class Attraction(
    @SerializedName("id") val id: Long,
    @SerializedName("cityId") val cityId: Long,
    @SerializedName("locationId") val locationId: Long,
    @SerializedName("location") val location: Location,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("images") val images: List<CityGuideImage> = emptyList()
) {
    companion object {
        fun fake(): Attraction = AttractionHolder.fake(1).data[0]
    }
}