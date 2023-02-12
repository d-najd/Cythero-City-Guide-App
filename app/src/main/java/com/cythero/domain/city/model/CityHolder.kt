package com.cythero.domain.city.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

data class CityHolder(
	@SerializedName("data") val `data`: List<City> = emptyList()
) {
	companion object {
		fun mock(): CityHolder = Injekt.get<Gson>().fromJson(
			MOCK_STRING,
			CityHolder::class.java
		)

		private const val MOCK_STRING = "" +
				"{\n" +
				"    \"data\": [\n" +
				"        {\n" +
				"            \"id\": 1,\n" +
				"            \"countryId\": 1,\n" +
				"            \"locationId\": 1,\n" +
				"            \"name\": \"Bitola\",\n" +
				"            \"images\": [\n" +
				"                {\n" +
				"                    \"id\": 1,\n" +
				"                    \"path\": \"http://somepath.com/img.jpg\",\n" +
				"                    \"cityId\": 1\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 2,\n" +
				"            \"countryId\": 1,\n" +
				"            \"locationId\": 2,\n" +
				"            \"name\": \"Skopje\",\n" +
				"            \"images\": []\n" +
				"        }\n" +
				"    ]\n" +
				"}"
	}
}
