package com.cythero.domain.attraction.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cythero.domain.attraction.interactor.GetAttraction
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AttractionPagingSource(
	private val getAttraction: GetAttraction = Injekt.get(),
): PagingSource<Long, Attraction>() {
	override fun getRefreshKey(state: PagingState<Long, Attraction>): Long? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}

	override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Attraction> {
		return try {
			val nextPageNumber = params.key ?: 1
			val attractionsHolder = getAttraction.awaitMulti(
				nextPageNumber,
				params.loadSize.toLong(), // TODO check if this is correct
			)
			LoadResult.Page(
				data = attractionsHolder,
				prevKey = null,
				nextKey = if (attractionsHolder.isNotEmpty()) nextPageNumber + 1 else null
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
}