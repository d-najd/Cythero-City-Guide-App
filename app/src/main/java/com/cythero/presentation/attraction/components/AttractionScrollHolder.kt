package com.cythero.presentation.attraction.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.cythero.domain.attraction.model.Attraction

/**
 * holder that holds the scroll "background" (transparent column that allows the content to be scrolled)
 * and its content which is located under the transparent column composable.
 * the background is the size of the screen of the device
 */
@Composable
fun AttractionScrollHolder(
	scrollState: ScrollState,
	attraction: Attraction,
	screenHeight: Dp,
	screenWidth: Dp,
) {
	AttractionScrollableSnapLogic(scrollState = scrollState)
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.verticalScroll(
				state = scrollState,
			)
			.heightIn(min = screenHeight)
			.padding(top = screenHeight),
	) {
		AttractionScrollableContent(
			scrollState = scrollState,
			screenHeight = screenHeight,
			screenWidth = screenWidth,
			attraction = attraction
		)
	}
}

@Composable
private fun AttractionScrollableSnapLogic(
	scrollState: ScrollState,
) {
	LaunchedEffect(scrollState.isScrollInProgress) {
		if(!scrollState.isScrollInProgress) {
			if(scrollState.value >= scrollState.maxValue/2) {
				scrollState.animateScrollTo(
					value = scrollState.maxValue,
					animationSpec = spring(
						stiffness = Spring.StiffnessMediumLow
					)
				)
			} else {
				scrollState.animateScrollTo(
					value = 0,
					animationSpec = spring(
						stiffness = Spring.StiffnessMediumLow
					)
				)
			}
		}
	}
}



/*
	 			val density = LocalDensity.current
			LaunchedEffect(!lazyListState.isScrollInProgress) {
				val firstVisibleDp = with(density) { lazyListState.firstVisibleItemScrollOffset.toDp() }
				if(firstVisibleDp > lazyListState.firstVisibleItemIndex * width + width/2) {
					if(lazyListState.firstVisibleItemIndex + 1 <= images.size) {
						lazyListState.animateScrollToItem(lazyListState.firstVisibleItemIndex + 1)
					}
				} else if (firstVisibleDp < lazyListState.firstVisibleItemIndex * width + width/2) {
					if(lazyListState.firstVisibleItemIndex - 1 >= 0) {
						lazyListState.animateScrollToItem(lazyListState.firstVisibleItemIndex - 1)
					}
				}
			}
			 */

