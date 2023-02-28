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
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.util.toast

@Composable
fun LibraryContent(
	state: LibraryScreenState.Success,
	contentPadding: PaddingValues,
	onAttractionClicked: (Long) -> Unit,
) {
	val lazyListState = rememberLazyListState()
	LazyColumn(
		modifier = Modifier
			.padding(contentPadding)
			.fillMaxSize(),
		state = lazyListState,
	) {
		val cities = state.attractions.toMutableList()
		items(cities) { attraction ->
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.height(225.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				// TODO make the spacer `blurry` from the items above and below (combine their
				//  colors instead of using onSurface color
				Spacer(
					modifier = Modifier
						.height(0.5.dp)
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.onSurface)
				)
				LibraryContainer(
					attraction = attraction,
					onAttractionClicked = onAttractionClicked,
				)
			}
		}

		item {
			CircularProgressIndicator()
		}
	}

	val context = LocalContext.current
	SideEffect {
		val itemCount = lazyListState.layoutInfo.totalItemsCount
		val lastVisibleItemIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
		if(lastVisibleItemIndex >= itemCount - 2) {
			context.toast("Loading more items")
		}
	}
}
