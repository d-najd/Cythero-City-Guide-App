package com.cythero.presentation.attraction.components

import android.graphics.drawable.Drawable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.domain.attraction.model.Attraction

/**
 * TODO make this more flexible in other words get rid of the secondScreenScrollState and instead calculate if
 *  250dp have been scrolled, since scrollState.value is not reliable something along the lines of the following
 *  formula should suffice
 *  { screenModel.maxValue - (composableHeight - (phoneScreenHeight + 250.dp)).toPercent() }
 *  NOTE the composable height and maxValue will change when the description will be expanded so beware of that
 */
@Composable
fun AttractionScrollableContent(
	scrollState: ScrollState,
	attraction: Attraction,
	height: Dp,
	width: Dp,
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
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.verticalScroll(
				state = scrollState,
			)
			.heightIn(min = height)
			.padding(top = height),
	) {
		val localDensity = LocalDensity.current
		var columnHeightDp by remember {
			mutableStateOf(0.dp)
		}
		val imageHeight = 375.dp
		val bottomButtonHeight = 75.dp
		val scrollProportion = scrollState.value.toFloat() / scrollState.maxValue.toFloat()
		val scrolledAmountInDp = columnHeightDp * scrollProportion


		BoxWithConstraints(modifier = Modifier
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.background)
			.onGloballyPositioned { coordinates ->  // Set column height using the LayoutCoordinates
				columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
			},
		) {
			AttractionScrollableTopContent(
				modifier = Modifier.background(MaterialTheme.colorScheme.surface)
					.fillMaxWidth()
					.padding(
						top = imageHeight + 12.dp,
						start = 20.dp,
						end = 20.dp,
					),
				spacerHeight = maxOf(
					height - columnHeightDp - bottomButtonHeight,
					20.dp
				),
				attraction = attraction,
			)
			AttractionScrollableBottomContent(
				modifier = Modifier.padding(
						top = maxOf(
							imageHeight,
							scrolledAmountInDp - bottomButtonHeight
						)
					)
					.height(bottomButtonHeight)
					.fillMaxWidth()
			)
			AttractionImageComposable(
				modifier = Modifier.height(imageHeight)
					.width(50000.dp),
				attraction = attraction,
				imageHeight = imageHeight,
				width = width,
			)
		}


		/*
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colorScheme.background)
				.onGloballyPositioned { coordinates ->  // Set column height using the LayoutCoordinates
					columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
				},
		) {
			AttractionImageComposable(
				modifier = Modifier
					.height(imageHeight)
					.width(50000.dp),
				attraction = attraction,
				imageHeight = imageHeight,
				width = width,
			)
			AttractionScrollableTopContent(
				modifier = Modifier
					.background(MaterialTheme.colorScheme.surface)
					.fillMaxWidth()
					.padding(
						top = 12.dp,
						start = 20.dp,
						end = 20.dp
					),
				attraction = attraction
			)
		}
		//spacer
		AttractionScrollableBottomContent(
			modifier = Modifier
				.height(bottomButtonHeight)
				.fillMaxWidth(),
		)

		 */
	}
}


@Composable
private fun AttractionImageComposable(
	modifier: Modifier,
	attraction: Attraction,
	imageHeight: Dp,
	width: Dp
) {
	val images = getFilteredImages(attraction)
	val lazyListState = rememberLazyListState()
	LazyRow(
		modifier = modifier,
		state = lazyListState,
	) {
		items(images) { image ->
			Image(
				bitmap = image.toBitmap().asImageBitmap(),
				contentDescription = null,
				modifier = Modifier
					.height(imageHeight)
					.width(width),
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
		}
	}
}



/*
Spacer(
	modifier = Modifier
		.background(color = MaterialTheme.colorScheme.background)
		.height(
			maxOf(
				height - columnHeightDp - 75.dp,
				20.dp
			)
		)
		.fillMaxWidth(),
)
 */


fun getFilteredImages(
	attraction: Attraction,
): List<Drawable> {
	val images = mutableListOf<Drawable>()
	if(attraction.location.flagPathDrawable != null) {
		images.add(attraction.location.flagPathDrawable)
	}
	attraction.images.mapNotNullTo(images) { it.pathDrawable }
	return images
}

/*
	val animatedVisibility by animateFloatAsState(
		targetValue = when (screenScrollState.valueInPercent()) {
			in -10f..20f -> {
				1f
			}
			in 20f..110f -> {
				max(
					0f,
					(100f - screenScrollState.valueInPercent()) / 80f
				)
			}
			else -> {
				1f
			}
		},
	)
	 */

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

