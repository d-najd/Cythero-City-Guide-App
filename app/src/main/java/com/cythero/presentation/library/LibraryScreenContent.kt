package com.cythero.presentation.library

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.ui.library.LibraryScreenState
import com.cythero.presentation.library.components.LibraryContent

@Composable
fun LibraryScreenContent(
    state: LibraryScreenState.Success,
    onBackClicked: () -> Unit,
) {
    Scaffold { contentPadding ->
        BackHandler { onBackClicked() }

        LibraryContent(
            state = state,
            contentPadding = contentPadding,
        )
    }
}
