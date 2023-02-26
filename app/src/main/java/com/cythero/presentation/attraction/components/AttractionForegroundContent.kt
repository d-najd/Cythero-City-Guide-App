package com.cythero.presentation.attraction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.domain.attraction.model.Attraction
import com.cythero.presentation.util.valueInPercent
import com.google.android.renderscript.Toolkit

/**
 * TODO apply shadow to the title and the rest of the content and check if it will be visible with pure white image
 */
@Composable
fun AttractionForegroundContent(
	scrollState: ScrollState,
	attraction: Attraction,
) {
	// TODO blur using library called cloudy should fix performance problems
	val blurAmount by remember(scrollState.value){ mutableStateOf(minOf(scrollState.valueInPercent()/4 + 1, 25f).toInt()) }
	val flagBitmap = attraction.location.flagPathDrawable!!.toBitmap()
	Box(
		modifier = Modifier
			.fillMaxSize()
	) {
		Image(
			modifier = Modifier.fillMaxSize(),
			bitmap = Toolkit.blur(flagBitmap, blurAmount).asImageBitmap(),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			alignment = Alignment.Center
		)
		val contentColor = Color.White	// background "filter", does not affect the text only the image
		Column(
			modifier = Modifier
				.fillMaxHeight()
				.padding(
					bottom = 26.dp,
					start = 24.dp,
					end = 24.dp,
				),
			verticalArrangement = Arrangement.Bottom,
		) {
			AttractionTitleComposable(
				modifier = Modifier.padding(bottom = 12.dp),
				title = attraction.name,
				contentColor = contentColor
			)
			AttractionAddressComposable(
				modifier = Modifier.padding(bottom = 26.dp),
				address = attraction.location.address,
				contentColor = contentColor,
			)
			Divider(
				thickness = .6.dp,
				color = contentColor.copy(.5f),
				modifier = Modifier
					.width(75.dp)
					.padding(bottom = 6.dp),
			)
			Row(
				verticalAlignment = Alignment.CenterVertically,
			) {
				AttractionPricePerNightComposable(
					contentColor = contentColor
				)
				AttractionRatingComposable(
					modifier = Modifier.fillMaxWidth(),
					contentColor = contentColor
				)
			}
		}
	}
}
