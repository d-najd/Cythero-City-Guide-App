package com.cythero.presentation.attraction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.cityguideapp.ui.attraction.AttractionScreenState

/**
 * TODO check if this screen works on other devices
 * TODO this needs hella lot of refactoring
 */
@Composable
fun AttractionContent(
	state: AttractionScreenState.Success,
	paddingValues: PaddingValues,
) {
	val attraction = state.attraction
	val scrollState = rememberScrollState()
	BoxWithConstraints(
		modifier = Modifier
			.padding(paddingValues),
	) {
		AttractionForegroundContent(
			scrollState = scrollState,
			attraction = attraction
		)
		AttractionScrollableContent(
			scrollState = scrollState,
			attraction = attraction,
			height = maxHeight,
		)
	}
}


