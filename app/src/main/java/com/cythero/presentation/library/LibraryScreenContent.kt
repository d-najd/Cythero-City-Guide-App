package com.cythero.presentation.library

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.R
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.presentation.library.components.LibraryContent

@Composable
fun LibraryScreenContent(
    state: LibraryScreenState.Success,
    onBackClicked: () -> Unit,
    onSortMenuClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // TODO animate the transition
                    if(!state.sortMenuEnabled) {
                        Text(
                            text = stringResource(R.string.field_popular_destinations),
                        )
                    } else {
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            val cornerRounding = 28.dp
                            // TODO this can be moved to an enum
                            val filters = listOf(
                                "Tourist Attractions",
                                "Restaurants",
                                "Hotels",
                                "Motels",
                                "Beaches",
                                "Gas Stations",
                                "Bars",
                            )

                            for(filter in filters) {
                                Button(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    // all elevations are multiplied by 2.5x
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 6.dp,
                                        pressedElevation = 24.dp,
                                        hoveredElevation = 12.dp,
                                        focusedElevation = 12.dp,
                                    ),
                                    onClick = { },
                                    shape = RoundedCornerShape(cornerRounding)
                                ) {
                                    Text(text = filter)
                                }
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onSortMenuClicked) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        BackHandler { onBackClicked() }

        LibraryContent(
            state = state,
            contentPadding = contentPadding,
        )
    }
}
