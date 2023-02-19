package com.cythero.presentation.attraction

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.cythero.cityguideapp.ui.attraction.AttractionScreenState
import com.cythero.presentation.attraction.components.AttractionContent

@Composable
fun AttractionScreenContent(
	state: AttractionScreenState.Success,
	onBackClicked: () -> Unit,
) {
	Scaffold { paddingValues ->
		BackHandler { onBackClicked() }

		AttractionContent(
			state = state,
			paddingValues = paddingValues,
		)
	}
}