package com.cythero.presentation.attraction.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cythero.domain.attraction.model.Attraction

@Composable
fun AttractionScrollableContent(
	scrollState: ScrollState,
	screenHeight: Dp,
	screenWidth: Dp,
	attraction: Attraction,
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
			modifier = Modifier
				.background(MaterialTheme.colorScheme.surface)
				.fillMaxWidth()
				.padding(
					top = imageHeight + 12.dp,
					start = 20.dp,
					end = 20.dp,
				),
			// TODO fix this
			spacerHeight = maxOf(
				screenHeight - columnHeightDp + bottomButtonHeight,
				20.dp + bottomButtonHeight
			),
			attraction = attraction,
		)
		AttractionScrollableBottomContent(
			modifier = Modifier
				.padding(
					top = maxOf(
						imageHeight,
						scrolledAmountInDp - bottomButtonHeight
					)
				)
				.height(bottomButtonHeight)
				.fillMaxWidth()
		)
		AttractionImagesComposable(
			modifier = Modifier
				.height(imageHeight)
				.width(50000.dp),
			attraction = attraction,
			imageHeight = imageHeight,
			imageWidth = screenWidth,
		)
	}
}