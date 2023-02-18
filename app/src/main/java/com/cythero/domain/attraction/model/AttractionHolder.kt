package com.cythero.domain.attraction.model

import com.cythero.domain.city.model.City
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

data class AttractionHolder(
	@SerializedName("data") val `data`: List<City> = emptyList()
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
				"            \"name\": \"Portal\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 2,\n" +
				"            \"cityId\": 2,\n" +
				"            \"locationId\": 1,\n" +
				"            \"name\": \"Portal2\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 4,\n" +
				"            \"cityId\": 2,\n" +
				"            \"locationId\": 1,\n" +
				"            \"name\": \"Portal3\",\n" +
				"            \"description\": \"Test\"\n" +
				"        }\n" +
				"    ]\n" +
				"}"
	}
}
