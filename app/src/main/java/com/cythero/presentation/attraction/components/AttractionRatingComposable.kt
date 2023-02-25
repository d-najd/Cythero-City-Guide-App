package com.cythero.presentation.attraction.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cythero.presentation.components.CircularProgressBar

@Composable
fun AttractionRatingComposable(
	modifier: Modifier = Modifier,
	contentColor: Color = Color.Unspecified,
){
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.End,
	) {
		Box(
			modifier = Modifier.size(67.5.dp),
			contentAlignment = Alignment.Center,
		) {
			val rating = 6.52f
			CircularProgressBar(
				startAngle = 180f,
				sweepAngle = 360f,
				strokeWidth = 4.dp,
				modifier = Modifier.size(67.5.dp),
				percentage = rating/10f,
				endIndicatorEnabled = false,
			)
			Text(
				text = rating.toString(),
				color = contentColor,
			)
		}
	}
}
