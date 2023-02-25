package com.cythero.presentation.attraction.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cythero.cityguideapp.R

@Composable
fun AttractionPricePerNightComposable(
	modifier: Modifier = Modifier,
	contentColor: Color,
) {
	Row(
		modifier = modifier,
	) {
		Text(
			color = contentColor,
			text = stringResource(R.string.field_from),
			fontWeight = FontWeight.ExtraLight,
		)
		Text(
			text = " $1,289 ",
			fontWeight = FontWeight.ExtraLight,
			color = MaterialTheme.colorScheme.primary,
		)
		Text(
			color = contentColor,
			text = stringResource(R.string.field_for_week),
			fontWeight = FontWeight.ExtraLight,
		)
	}
}
