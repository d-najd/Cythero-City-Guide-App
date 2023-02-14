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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.cythero.cityguideapp.theme.DestinationThemeFactory
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import kotlinx.coroutines.DelicateCoroutinesApi

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
		items(state.cities) { city ->
			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {

				var columnModifier = Modifier
					.width((355.5555).dp)
					.height(200.dp)
					.padding(8.dp)
				val drawable = city.images[0].drawable
				drawable?.let {
					columnModifier = columnModifier.paint(
						painter = BitmapPainter(drawable.toBitmap().asImageBitmap())
					)
				}
				Column(
					modifier = columnModifier,
				) {
					val themeColor = DestinationThemeFactory.getRandomColor()

					Box(
						modifier = Modifier
							.fillMaxSize()
							.background(themeColor.copy(.4f))
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
									imageVector = Icons.Default.Favorite,
									contentDescription = ""
								)
							}
							Text(
								modifier = Modifier.padding(start = 2.dp),
								text = "5.0K"
							)
						}

						Column(
							modifier = Modifier
								.fillMaxSize()
								.padding(bottom = paddingCentered),
							horizontalAlignment = Alignment.CenterHorizontally,
							verticalArrangement = Arrangement.Center,
						) {
							Text(
								text = city.name.uppercase(),
								fontFamily = FontFamily.SansSerif,
								fontWeight = FontWeight.SemiBold,
								letterSpacing = 4.sp,
								fontSize = 25.sp,
							)

							Button(
								colors = ButtonDefaults.buttonColors(
									disabledBackgroundColor = contentColorFor(themeColor).copy(alpha = .15f),
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
			}
		}
	}
}