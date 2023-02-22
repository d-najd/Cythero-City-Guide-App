package com.cythero.presentation.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.presentation.library.components.LibraryContent
import com.cythero.presentation.library.components.LibraryTopAppBar

@Composable
fun LibraryScreenContent(
    state: LibraryScreenState.Success,
    onSortMenuClicked: () -> Unit,
    onAttractionClicked: (Long) -> Unit,
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
            onAttractionClicked = onAttractionClicked,
        )
    }
}
