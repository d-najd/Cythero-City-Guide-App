package com.cythero.domain.city.model


import com.cythero.domain.image_url.model.Image
import com.google.gson.annotations.SerializedName


data class City(
    @SerializedName("id") val id: Long,
    @SerializedName("countryId") val countryId: Long,
    @SerializedName("locationId") val locationId: Long,
    @SerializedName("name") val name: String,
    @SerializedName("images") val images: List<Image> = emptyList(),
) {
    companion object {
        fun fake(): City = CityHolder.fake(1).data[0]
    }
}