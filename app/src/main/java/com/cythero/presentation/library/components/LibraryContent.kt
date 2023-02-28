package com.cythero.presentation.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.util.toast
import kotlinx.coroutines.flow.asFlow

@Composable
fun LibraryContent(
	state: LibraryScreenState.Success,
	contentPadding: PaddingValues,
	onAttractionClicked: (Long) -> Unit,
) {
	val lazyListState = rememberLazyListState()
	val attractionLazyPager = state.attractionPager.collectAsLazyPagingItems()
	LazyColumn(
		modifier = Modifier.padding(contentPadding)
			.fillMaxSize(),
		state = lazyListState,
	) {
		items(attractionLazyPager) { attraction ->
			attraction?.let {
				Column(
					modifier = Modifier.fillMaxWidth()
						.height(225.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {                // TODO make the spacer `blurry` from the items above and below (combine their
					//  colors instead of using onSurface color
					Spacer(
						modifier = Modifier.height(0.5.dp)
							.fillMaxWidth()
							.background(MaterialTheme.colorScheme.onSurface)
					)
					LibraryContainer(
						attraction = attraction,
						onAttractionClicked = onAttractionClicked,
					)
				}
			}
		}
		when (attractionLazyPager.loadState.append) {
			is LoadState.NotLoading -> Unit
			LoadState.Loading -> {
				item {					// LoadingItem()

				}
			}
			is LoadState.Error -> {
				item {					// ErrorItem(message = (usersData.loadState.append as LoadState.Error).error.message.toString())
				}
			}
		}
	}
}

