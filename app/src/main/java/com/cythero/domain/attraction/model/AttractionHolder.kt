package com.cythero.domain.attraction.model

import com.cythero.domain.city.model.City
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

data class AttractionHolder(
	@SerializedName("data") val `data`: List<Attraction> = emptyList()
) {
	companion object {
		fun mock(): AttractionHolder = Injekt.get<Gson>().fromJson(
			MOCK_STRING,
			AttractionHolder::class.java
		)

		private const val MOCK_STRING = "" +
				"{\n" +
				"    \"data\": [\n" +
				"        {\n" +
				"            \"id\": 1,\n" +
				"            \"cityId\": 2,\n" +
				"            \"locationId\": 1,\n" +
				"            \"location\": {\n" +
				"                \"id\": 1,\n" +
				"                \"flagPath\": \"https://picsum.photos/1080/720\",\n" +
				"                \"address\": \"Macedonia - Bitola?\"\n" +
				"            },\n" +
				"            \"name\": \"Portal\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 2,\n" +
				"            \"cityId\": 2,\n" +
				"            \"locationId\": 1,\n" +
				"            \"location\": {\n" +
				"                \"id\": 1,\n" +
				"                \"flagPath\": \"https://picsum.photos/1080/720\",\n" +
				"                \"address\": \"Macedonia - Bitola?\"\n" +
				"            },\n" +
				"            \"name\": \"Portal2\"\n" +
				"        }\n" +
				"    ]\n" +
				"}"
	}
}
