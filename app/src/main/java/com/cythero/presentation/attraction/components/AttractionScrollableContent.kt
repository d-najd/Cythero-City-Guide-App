package com.cythero.presentation.attraction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
	attraction: Attraction,
	height: Dp,
) {
	val screenScrollState = rememberScrollState()
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.verticalScroll(screenScrollState)
			.heightIn(min = height)
			.padding(top = height),
	) {
		val localDensity = LocalDensity.current
		var columnHeightDp by remember {
			mutableStateOf(0.dp)
		}
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colorScheme.background)
				.onGloballyPositioned { coordinates ->  // Set column height using the LayoutCoordinates
					columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
				},
		) {
			Image(
				bitmap = attraction.location.flagPathDrawable!!.toBitmap()
					.asImageBitmap(),
				contentDescription = null,
				modifier = Modifier
					.height(350.dp)
					.fillMaxWidth(),
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
			AttractionScrollableTopContent(attraction = attraction)
		}
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
		AttractionScrollableBottomContent()
	}
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