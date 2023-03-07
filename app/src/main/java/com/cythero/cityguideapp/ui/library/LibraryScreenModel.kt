package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Immutable
import androidx.paging.*
import cafe.adriel.voyager.core.model.coroutineScope
import com.bumptech.glide.request.RequestOptions
import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.attraction.model.AttractionPagingSource
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

@OptIn(ExperimentalCoroutinesApi::class)
class LibraryScreenModel(
	private val getImageByUrl: GetImageByUrl = Injekt.get(),
) : CityGuideStateScreenModel<LibraryScreenState>(LibraryScreenState.Loading) {
	private fun successState(): LibraryScreenState.Success = mutableState.value as LibraryScreenState.Success
	private val attractionPager = Pager(PagingConfig(pageSize = 5, prefetchDistance = 2, initialLoadSize = 6)) {
		AttractionPagingSource(
			onLoading = { loadState ->
				coroutineScope.launch {
					mutableState.update {
						successState().copy(
							isLoading = loadState,
						)
					}
				}
			},
			onEndReached = { endReached ->
				coroutineScope.launch {
					mutableState.update {
						successState().copy(
							endReached = endReached,
						)
					}
				}
			},
		)
	}.flow.cachedIn(coroutineScope)

	init {
		coroutineScope.launchIO {
			mutableState.update {
				LibraryScreenState.Success(
					attractionPager = attractionPager,
				)
			}
			fetchFlagPaths()
		}
	}

	/** must be launched for io scope */
	private suspend fun fetchFlagPaths() {
		mutableState.update {
			successState().copy(
				attractionPager = attractionPager.mapLatest { data -> data.filter { it.location.flagPathDrawable == null  } .map { attraction ->
					val requestOptions = RequestOptions.fitCenterTransform().centerCrop()
					val drawable = getImageByUrl.subscribeOne(
						attraction.location.flagPath,
						requestOptions,
					)
					attraction.copy(
						location = attraction.location.copy(
							flagPathDrawable = drawable,
						)
					)
				}}
			)
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
		val endReached: Boolean = false,
		val isLoading: Boolean = false,
		val attractionPager: Flow<PagingData<Attraction>>,
		val attractionsToLoadImages: List<Long> = emptyList(),
		val sortMenuEnabled: Boolean = false,
	): LibraryScreenState()

}
