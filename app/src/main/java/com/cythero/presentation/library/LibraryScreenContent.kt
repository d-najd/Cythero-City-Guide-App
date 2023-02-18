package com.cythero.presentation.library

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.cythero.presentation.library.components.LibraryTopAppBar

@Composable
fun LibraryScreenContent(
    state: LibraryScreenState.Success,
    onSortMenuClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            LibraryTopAppBar(
                state = state,
                onSortMenuClicked = onSortMenuClicked,
            )
        }
    ) { contentPadding ->
        LibraryContent(
            state = state,
            contentPadding = contentPadding,
        )
    }
}
