package com.cythero.presentation.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.ui.library.LibraryScreenState

@Composable
fun LibraryScreenContent(
    state: LibraryScreenState.Success,
    onBackClicked: () -> Unit,
) {
    Scaffold { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding).padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Hello World")
        }
    }
}
