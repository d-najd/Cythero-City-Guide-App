package com.cythero.cityguideapp.ui.review

import androidx.compose.runtime.Immutable
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.domain.review.model.Review
import com.cythero.domain.review.model.ReviewPagingSource
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReviewScreenModel(
	private val attractionId: Long,
) : CityGuideStateScreenModel<ReviewScreenState>(ReviewScreenState.Loading) {
	private fun successState(): ReviewScreenState.Success = mutableState.value as ReviewScreenState.Success
	private val pager = Pager(
		PagingConfig(
			pageSize = 10,
			prefetchDistance = 2,
			initialLoadSize = 25,
		)
	) {
		ReviewPagingSource(
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
				ReviewScreenState.Success(
					pager = pager,
				)
			}
		}
	}
}

sealed class ReviewScreenState {
	@Immutable
	object Loading : ReviewScreenState()

	@Immutable
	data class Success(
		val endReached: Boolean = false,
		val isLoading: Boolean = false,
		val pager: Flow<PagingData<Review>>,
	): ReviewScreenState()
}