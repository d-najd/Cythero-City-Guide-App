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
				"            \"images\": [\n" +
				"                {\n" +
				"                    \"id\": 2,\n" +
				"                    \"path\": \"https://imgs.search.brave.com/jSJFZcsKAbhadCjpcyjf3rd4YgiwuqrHqanjps1YkFc/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly9scC1j/bXMtcHJvZHVjdGlv/bi5pbWdpeC5uZXQv/MjAxOS0wNi80NTA5/Mjg0NDhfbWFzdGVy/LmpwZz9maXQ9Y3Jv/cCZxPTQwJnNoYXJw/PTEwJnZpYj0yMCZh/dXRvPWZvcm1hdCZp/eGxp\",\n" +
				"                    \"cityId\": 2\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 3,\n" +
				"            \"countryId\": 1,\n" +
				"            \"locationId\": 3,\n" +
				"            \"name\": \"Prilep\",\n" +
				"            \"images\": [\n" +
				"                {\n" +
				"                    \"id\": 3,\n" +
				"                    \"path\": \"https://imgs.search.brave.com/n-JHTPa843cieE7Zg419Q-enBc4gA-vO8hV322a9two/rs:fit:1000:667:1/g:ce/aHR0cHM6Ly93d3cu/Z3BzbXljaXR5LmNv/bS9pbWcvYWRwX2Nv/dmVyLzMzNzcuanBn\",\n" +
				"                    \"cityId\": 3\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 4,\n" +
				"            \"countryId\": 1,\n" +
				"            \"locationId\": 4,\n" +
				"            \"name\": \"Kavadarci\",\n" +
				"            \"images\": []\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 5,\n" +
				"            \"countryId\": 1,\n" +
				"            \"locationId\": 5,\n" +
				"            \"name\": \"Tetovo\",\n" +
				"            \"images\": []\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 6,\n" +
				"            \"countryId\": 1,\n" +
				"            \"locationId\": 6,\n" +
				"            \"name\": \"Ohrid\",\n" +
				"            \"images\": [\n" +
				"                {\n" +
				"                    \"id\": 6,\n" +
				"                    \"path\": \"https://imgs.search.brave.com/OYdLyqtZRwwitBfn0IWLf8vakswLUUwGFIocwucNZGw/rs:fit:632:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5r/ZDhjekV6WjVQNDRT/RURmR0IyOUdnSGFG/aiZwaWQ9QXBp\",\n" +
				"                    \"cityId\": 6\n" +
				"                }\n" +
				"            ]\n" +
				"        }\n" +
				"    ]\n" +
				"}"
	}
}
