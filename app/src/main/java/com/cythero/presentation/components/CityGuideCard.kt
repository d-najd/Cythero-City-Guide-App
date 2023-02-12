package com.cythero.presentation.components

import com.cythero.cityguideapp.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @sample BugtrackerCardPreview()
 */
@Composable
fun CityGuideCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    applyPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(if(applyPadding) ANALYTICS_CARD_PADDING else PaddingValues(0.dp)),
    ) {

        Column(modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 16.dp)
        ) {
            if(title != null) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(ANALYTICS_CARD_CONTENT_PADDING)
                        .padding(vertical = 16.dp),
                )
            }
            content()
        }
    }
}

@Preview(
    widthDp = 300,
)
@Composable
private fun BugtrackerCardPreview(){
    var dismissMenu by remember { mutableStateOf(false) }

    CityGuideCard(title = "title") {
        CityGuideIconPairField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            title = "Reporter",
            text = "user1",
            iconContent = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = ""
                )
            },
        )
    }
}

val ANALYTICS_CARD_CONTENT_PADDING = PaddingValues(vertical = 6.dp)
private val ANALYTICS_CARD_PADDING = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
