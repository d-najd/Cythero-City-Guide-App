package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Immutable
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.coroutineScope
import com.bumptech.glide.request.RequestOptions
import com.cythero.domain.attraction.interactor.GetAttraction
import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionPagingSource
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class LibraryScreenModel(
	private val getAttraction: GetAttraction = Injekt.get(),
	private val getImageByUrl: GetImageByUrl = Injekt.get(),
) : CityGuideStateScreenModel<LibraryScreenState>(LibraryScreenState.Loading) {
	private fun successState(): LibraryScreenState.Success = mutableState.value as LibraryScreenState.Success
	init {
		coroutineScope.launchIO {
			val attractions = getAttraction.awaitMulti(page = 1).toMutableList()
			val attractionPager = Pager(PagingConfig(pageSize = 5, prefetchDistance = 2, initialLoadSize = 6)) {
				AttractionPagingSource()
			}.flow.cachedIn(coroutineScope)
			mutableState.update {
				LibraryScreenState.Success(
					attractions = attractions,
					attractionPager = attractionPager
				)
			}
			for(attraction in attractions) {
				val requestOptions = RequestOptions.fitCenterTransform().centerCrop()
				val drawable = getImageByUrl.subscribeOne(attraction.location.flagPath, requestOptions)
				val attractionWithFlag = attraction.copy(
					location = attraction.location.copy(
						flagPathDrawable = drawable
					)
				)
				attractions[attractions.indexOf(attraction)] = attractionWithFlag
			}
			mutableState.update {
				successState().copy(
					attractions = attractions,
				)
			}
		}
	}

	fun invertSortMenu() {
		coroutineScope.launch {
			mutableState.update {
				val successState = successState()
				successState.copy(
					sortMenuEnabled = !successState.sortMenuEnabled,
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
		// TODO this is not supported to hold cities but rather locations.......
		val attractionPager: Flow<PagingData<Attraction>>,
		val attractions: List<Attraction>,
		val sortMenuEnabled: Boolean = false,
	): LibraryScreenState()

}
