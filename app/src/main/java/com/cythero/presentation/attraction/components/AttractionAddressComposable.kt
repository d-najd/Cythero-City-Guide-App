package com.cythero.presentation.attraction.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.cythero.presentation.components.CityGuideIconPairField

@Composable
fun AttractionAddressComposable(
	modifier: Modifier = Modifier,
	address: String,
	contentColor: Color = LocalContentColor.current,
) {
	CityGuideIconPairField(
		modifier = modifier,
		iconContent = {
			Icon(
				tint = contentColor,
				imageVector = Icons.Sharp.LocationOn,
				contentDescription = null,
			)
		},
		textContent = {
			Text(
				text = address,
				fontWeight = FontWeight.ExtraLight,
				color = contentColor,
			)
		}
	)
}
