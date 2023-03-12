package com.cythero.presentation.attraction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.R

@Composable
fun AttractionScrollableBottomContent(
	modifier: Modifier,
) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Bottom
	) {
		Button(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.primary),
			onClick = { },
			shape = RoundedCornerShape(0.dp),
		) {
			Text(
				text = stringResource(R.string.field_get_directions),
			)
		}
	}
}
