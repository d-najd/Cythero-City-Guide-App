package com.cythero.presentation.library.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LibraryLikesTabComposable(
	modifier: Modifier = Modifier,
	contentColor: Color,
	paddingCentered: Dp,
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
	) {
		IconButton(
			modifier = Modifier
				.padding(start = 4.dp)
				.size(paddingCentered),
			onClick = { /*TODO*/ }
		) {
			Icon(
				tint = contentColor,
				imageVector = Icons.Default.Favorite,
				contentDescription = ""
			)
		}
		Text(
			modifier = Modifier.padding(start = 2.dp),
			color = contentColor,
			text = "5.0K"
		)
	}
}
