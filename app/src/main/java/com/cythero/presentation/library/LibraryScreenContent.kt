package com.cythero.presentation.library

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
