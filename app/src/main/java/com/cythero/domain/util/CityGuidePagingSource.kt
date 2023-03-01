package com.cythero.domain.util

import androidx.paging.PagingSource

abstract class CityGuidePagingSource<Key : Any, Value : Any>(
	internal open inline val onLoading: (Boolean) -> Unit,
	internal open inline val onEndReached: (Boolean) -> Unit,
): PagingSource<Key, Value>()