package com.cythero.presentation.attraction.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.domain.attraction.model.Attraction

@Composable
fun AttractionImagesComposable(
	modifier: Modifier,
	attraction: Attraction,
	imageHeight: Dp,
	imageWidth: Dp
) {
	val images = getFilteredImages(attraction)
	val lazyListState = rememberLazyListState()
	LazyRow(
		modifier = modifier,
		state = lazyListState,
	) {
		items(images) { image ->
			Image(
				bitmap = image.toBitmap().asImageBitmap(),
				contentDescription = null,
				modifier = Modifier
					.height(imageHeight)
					.width(imageWidth),
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
		}
	}
}

fun getFilteredImages(
	attraction: Attraction,
): List<Drawable> {
	val images = mutableListOf<Drawable>()
	if(attraction.location.flagPathDrawable != null) {
		images.add(attraction.location.flagPathDrawable)
	}
	attraction.images.mapNotNullTo(images) { it.pathDrawable }
	return images
}
