package com.cythero.presentation.attraction.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AttractionTitleComposable(
	modifier: Modifier = Modifier,
	title: String,
	contentColor: Color = Color.Unspecified,
) {
	Text(
		modifier = modifier,
		color = contentColor,
		text = title,
		fontWeight = FontWeight.Bold,
		fontSize = 52.sp,
		letterSpacing = 5.sp,
	)
}
