package com.cythero.presentation.attraction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
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