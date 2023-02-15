package com.cythero.presentation.library.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.cityguideapp.theme.DestinationThemeFactory
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.domain.city.model.City

@Composable
fun LibraryContent(
	state: LibraryScreenState.Success,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		modifier = Modifier
			.padding(contentPadding)
			.fillMaxSize()
	) {
		val cities = state.cities.toMutableList()

		items(cities) { city ->
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp)
					.padding(vertical = 8.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Box(
					modifier = Modifier.width(355.5555.dp),
				) {
					val drawable = city.images[0].drawable

					drawable?.let {
						Image(
							bitmap = drawable.toBitmap().asImageBitmap(),
							contentDescription = null,
							modifier = Modifier
								.aspectRatio(355.5555f / (200f - 16f))
								.fillMaxSize(),
							contentScale = ContentScale.Crop,
							alignment = Alignment.Center
						)
					}

					Column(
						modifier = Modifier.fillMaxSize()
					) {
						InsideContent(city = city)
					}

				}
			}
		}
	}
}

@Composable
private fun InsideContent(
	city: City
) {
	val themeColor = DestinationThemeFactory.getRandomColor()
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(themeColor.copy(.55f))
	) {
		val paddingCentered = 36.dp

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.height(paddingCentered)
		) {
			IconButton(
				modifier = Modifier.size(paddingCentered),
				onClick = { /*TODO*/ }
			) {
				Icon(
					// TODO for some reason the color does not want to be set
					tint = contentColorFor(backgroundColor = contentColorFor(backgroundColor = themeColor)),
					imageVector = Icons.Default.Favorite,
					contentDescription = ""
				)
			}
			Text(
				color = contentColorFor(backgroundColor = themeColor),
				modifier = Modifier.padding(start = 2.dp),
				text = "5.0K"
			)
		}

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(bottom = paddingCentered/2f),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
		) {
			Text(
				text = city.name.uppercase(),
				fontFamily = FontFamily.SansSerif,
				fontWeight = FontWeight.SemiBold,
				color = contentColorFor(backgroundColor = themeColor),
				letterSpacing = 4.sp,
				fontSize = 25.sp,
			)

			Button(
				colors = ButtonDefaults.buttonColors(
					disabledBackgroundColor = themeColor.copy(.825f),
					disabledContentColor = contentColorFor(backgroundColor = themeColor.copy(.825f))
				),
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
}