package com.cythero.presentation.attraction.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cythero.domain.attraction.model.Attraction

/**
 * TODO apply shadow to the title and the rest of the content and check if it will be visible with pure white image
 */
@Composable
fun AttractionForegroundContent(
	modifier: Modifier = Modifier,
	attraction: Attraction,
	contentColor: Color,
) {
	Column(
		modifier = modifier,
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
