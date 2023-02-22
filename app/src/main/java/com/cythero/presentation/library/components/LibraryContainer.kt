package com.cythero.presentation.library.components

import LibraryContainerContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.core.graphics.drawable.toBitmap
import com.cythero.cityguideapp.theme.DestinationThemeFactory
import com.cythero.domain.attraction.model.Attraction

/**
 * Displays the background attraction image and its content if { attraction.location.flagPathDrawable } is not null
 * @param attraction the attraction
 * @param onAttractionClicked action when the attraction is clicked
 */
@Composable
fun LibraryContainer(
	attraction: Attraction,
	onAttractionClicked: (Long) -> Unit,
) {
	Box {
		attraction.location.flagPathDrawable?.let {
			Image(
				bitmap = it.toBitmap().asImageBitmap(),
				contentDescription = null,
				modifier = Modifier
					.fillMaxSize(),
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
		}
		val themeColor = DestinationThemeFactory.getRandomColor()
		Column(
			modifier = Modifier
				.fillMaxSize()
				.clickable { onAttractionClicked(attraction.id) }
				.background(themeColor.copy(.4f))
		) {
			LibraryContainerContent(
				attraction = attraction,
				themeColor = themeColor
			)
		}
	}
}
