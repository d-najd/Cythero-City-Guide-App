package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.bumptech.glide.request.RequestOptions
import com.cythero.domain.city.interactor.GetCity
import com.cythero.domain.city.model.City
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class LibraryScreenModel(
	private val getCity: GetCity = Injekt.get(),
	private val getImageByUrl: GetImageByUrl = Injekt.get(),
) : CityGuideStateScreenModel<LibraryScreenState>(LibraryScreenState.Loading) {
	private fun successState(): LibraryScreenState.Success = mutableState.value as LibraryScreenState.Success

	init {
		coroutineScope.launchIO {
			val cities = getCity.awaitAll().toMutableList()
			mutableState.update {
				LibraryScreenState.Success(
					cities = cities
				)
			}

			for(city in cities) {
				val requestOptions = RequestOptions.fitCenterTransform().centerCrop()
				val drawable = getImageByUrl.subscribeOne(city.images[0].path, requestOptions)
				val cityImages = city.images.toMutableList()
				cityImages[0] = cityImages[0].copy(
					drawable = drawable
				)
				cities[cities.indexOf(city)] = city.copy(
					images = cityImages
				)
				mutableState.update {
					successState().copy(
						cities = cities,
					)
				}
			}
		}
	}

	fun invertSortMenu() {
		coroutineScope.launch {
			mutableState.update {
				val successState = successState()
				successState.copy(
					sortMenuEnabled = !successState.sortMenuEnabled
				)
			}
		}
	}

	/*
	sealed class Event: BaseEvent() {
		object Test: Event()
		object CanNotGetParentTable : LocalizedMessage(R.string.app_name)
	}
	 */
}

sealed class LibraryScreenState {

	@Immutable
	object Loading : LibraryScreenState()

	@Immutable
	data class Success(
		val cities: List<City>,
		val sortMenuEnabled: Boolean = false,
	): LibraryScreenState()

}
