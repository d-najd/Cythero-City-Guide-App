package com.cythero.domain.review.model

import androidx.paging.PagingState
import com.cythero.domain.review.interactor.GetReview
import com.cythero.domain.util.CityGuidePagingSource
import com.cythero.util.withIOContext
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class ReviewPagingSource(
	private val getReview: GetReview = Injekt.get(),
	override val onLoading: (Boolean) -> Unit,
	override val onEndReached: (Boolean) -> Unit,
): CityGuidePagingSource<Long, Review>(
	onLoading = onLoading,
	onEndReached = onEndReached
) {
	override fun getRefreshKey(state: PagingState<Long, Review>): Long? =
		state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}

	override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Review> = withIOContext {
		try {
			onLoading(true)
			val nextPageNumber = params.key ?: 0
			val attractionsHolder = getReview.awaitMulti(
				nextPageNumber,
				params.loadSize.toLong()
			)
			onLoading(false)
			if (attractionsHolder.isEmpty()) onEndReached(true)
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