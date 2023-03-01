package com.cythero.domain.attraction.model

import androidx.paging.PagingState
import com.cythero.domain.attraction.interactor.GetAttraction
import com.cythero.domain.util.CityGuidePagingSource
import com.cythero.util.withIOContext
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AttractionPagingSource(
	private val getAttraction: GetAttraction = Injekt.get(),
	override val onLoading: (Boolean) -> Unit,
	override val onEndReached: (Boolean) -> Unit,
): CityGuidePagingSource<Long, Attraction>(
	onLoading = onLoading,
	onEndReached = onEndReached
) {
	override fun getRefreshKey(state: PagingState<Long, Attraction>): Long? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}

	override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Attraction> {
		return withIOContext {
			try {
				onLoading(true)
				val nextPageNumber = params.key ?: 0
				val attractionsHolder = getAttraction.awaitMulti(nextPageNumber, params.loadSize.toLong())
				onLoading(false)
				if(attractionsHolder.isEmpty()) onEndReached(true)
				LoadResult.Page(
					data = attractionsHolder,
					prevKey = null,
					nextKey = if (attractionsHolder.isNotEmpty()) nextPageNumber + 1 else null
				)
			} catch (e: Exception) {
				onLoading(false)
				LoadResult.Error(e)
			}
		}
	}
}