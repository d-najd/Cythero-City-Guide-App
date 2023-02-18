package com.cythero.domain.attraction.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

data class Attraction(
    @SerializedName("id") val id: Long,
    @SerializedName("cityId") val cityId: Long,
    @SerializedName("locationId") val locationId: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
) {
    companion object {
        fun mock(): Attraction = Injekt.get<Gson>().fromJson(
            MOCK_STRING,
            Attraction::class.java
        )

        private const val MOCK_STRING = "" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"cityId\": 2,\n" +
                "            \"locationId\": 1,\n" +
                "            \"name\": \"Portal3\",\n" +
                "            \"description\": \"Test\"\n" +
                "        }"
    }
}