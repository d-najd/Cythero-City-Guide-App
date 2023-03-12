package com.cythero.presentation.attraction.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cythero.cityguideapp.ui.attraction.AttractionScreenState

/**
 * foreground content is the content that we see that pops up when we open the activity.
 * the scrollContentHolder contains the content that we see when the screen gets scrolled.
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
		AttractionScrollHolder(
			scrollState = scrollState,
			attraction = attraction,
			screenHeight = maxHeight,
			screenWidth = maxWidth,
		)
	}
}


