package com.cythero.presentation.library.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.cityguideapp.theme.DestinationThemeFactory
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.domain.attraction.model.Attraction

@Composable
fun LibraryContent(
	state: LibraryScreenState.Success,
	contentPadding: PaddingValues,
	onAttractionClicked: (Long) -> Unit,
) {
	LazyColumn(
		modifier = Modifier
			.padding(contentPadding)
			.fillMaxSize(),
	) {
		val cities = state.attractions.toMutableList()
		items(cities) { attraction ->
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.height(225.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				// TODO make the spacer `blurry` from the items above and below (combine their
				//  colors instead of using onSurface color
				Spacer(
					modifier = Modifier
						.height(0.5.dp)
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.onSurface)
				)
				AttractionContentContainer(
					attraction = attraction,
					onAttractionClicked = onAttractionClicked,
				)
			}
		}
	}
}

/**
 * Displays the background attraction image and its content if { attraction.location.flagPathDrawable } is not null
 * @param attraction the attraction
 * @param onAttractionClicked action when the attraction is clicked
 */
@Composable
private fun AttractionContentContainer(
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
			AttractionForegroundContent(
				attraction = attraction,
				themeColor = themeColor
			)
		}
	}
}

@Composable
private fun AttractionForegroundContent(
	attraction: Attraction,
	themeColor: Color,
) {
	// Color of the text and the rest of the stuff inside the column
	val contentColor = Color.White
	Box(
		modifier = Modifier
			.fillMaxSize(),
	) {
		val paddingCentered = 36.dp
		LikesTabComposable(
			contentColor = contentColor,
			paddingCentered = paddingCentered,
		)

		AttractionContainerCenterContent(
			attraction = attraction,
			themeColor = themeColor,
			contentColor = contentColor,
			paddingCentered = paddingCentered,
		)
	}
}

@Composable
private fun LikesTabComposable(
	contentColor: Color,
	paddingCentered: Dp,
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.height(paddingCentered)
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

@Composable
private fun AttractionContainerCenterContent(
	attraction: Attraction,
	themeColor: Color,
	contentColor: Color,
	paddingCentered: Dp,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(bottom = paddingCentered / 2f),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Text(
			text = attraction.name.uppercase(),
			fontFamily = FontFamily.SansSerif,
			fontWeight = FontWeight.SemiBold,
			color = contentColor,
			letterSpacing = 3.75.sp,
			fontSize = 28.sp,
		)
		Button(
			colors = ButtonDefaults.buttonColors(
				disabledContainerColor = themeColor.copy(.9f),
				disabledContentColor = contentColor,
			),
			shape = RoundedCornerShape(24.dp),
			enabled = false,
			onClick = { /*TODO*/ }
		) {
			Text(
				text = "2,642 VISITORS",
				fontFamily = FontFamily.SansSerif,
				fontWeight = FontWeight.ExtraLight,
				fontSize = 15.sp,
			)
		}
	}
}