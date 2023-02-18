package com.cythero.presentation.library.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
					.height(225.dp),
			horizontalAlignment = Alignment.CenterHorizontally
			) {
				// TODO make the spacer `blurry` from the items above and below (combine their
				//  colors instead of using onSurface color
				Spacer(modifier = Modifier
					.height(.5.dp)
					.fillMaxWidth()
					.background(MaterialTheme.colors.onSurface)
				)
				Box {
					val drawable = city.images[0].drawable
					drawable?.let {
						Image(
							bitmap = drawable.toBitmap().asImageBitmap(),
							contentDescription = null,
							modifier = Modifier
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
	city: City,
) {
	// Color of the text and the rest of the stuff inside the column
	val contentColor = Color.White
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
				modifier = Modifier
					.padding(start = 4.dp)
					.size(paddingCentered),
				onClick = { /*TODO*/ }
			) {
				Icon(
					// TODO for some reason the color does not want to be set
					tint = contentColor,
					imageVector = Icons.Default.Favorite,
					contentDescription = ""
				)
			}
			Text(
				color = contentColor,
				modifier = Modifier.padding(start = 2.dp),
				text = "5.0K"
			)
		}

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(bottom = paddingCentered / 2f),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
		) {
			Text(
				text = city.name.uppercase(),
				fontFamily = FontFamily.SansSerif,
				fontWeight = FontWeight.SemiBold,
				color = contentColor,
				letterSpacing = 4.sp,
				fontSize = 25.sp,
			)

			Button(
				colors = ButtonDefaults.buttonColors(
					disabledBackgroundColor = themeColor.copy(.9f),
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
}
