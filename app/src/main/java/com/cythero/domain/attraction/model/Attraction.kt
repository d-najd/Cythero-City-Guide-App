package com.cythero.domain.attraction.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

data class Attraction(
    @SerializedName("id") val id: Long,
    @SerializedName("cityId") val cityId: Long,
    @SerializedName("locationId") val locationId: Long,
    @SerializedName("location") val location: Location,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
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
                "            \"location\": {\n" +
                "                \"id\": 1,\n" +
                "                \"flagPath\": \"https://picsum.photos/1080/720\"\n" +
                "            }," +
                "            \"name\": \"Portal3\",\n" +
                "            \"description\": \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\"\n" +
                "        }"
    }
}