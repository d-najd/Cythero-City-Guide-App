package com.cythero.presentation.library.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cythero.cityguideapp.ui.library.LibraryScreenState

@Composable
fun LibraryContent(
	state: LibraryScreenState.Success,
	onBackClicked: () -> Unit,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		modifier = Modifier
			.padding(contentPadding)
			.fillMaxSize()
	) {
		items(state.cities) { city ->
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.height(225.dp)
					.padding(8.dp),
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically,
				) {
					IconButton(onClick = { /*TODO*/ }) {
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
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center,
				) {
					Text(
						text = city.name.uppercase(),
						fontFamily = FontFamily.SansSerif,
						fontWeight = FontWeight.SemiBold,
						fontSize = 20.sp,
					)

					Button(
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