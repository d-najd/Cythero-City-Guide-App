import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cythero.domain.attraction.model.Attraction
import com.cythero.presentation.library.components.LibraryContainerCenterContent
import com.cythero.presentation.library.components.LibraryLikesTabComposable

@Composable
fun LibraryContainerContent(
	attraction: Attraction,
	themeColor: Color,
) {
	// Color of the text and the rest of the stuff inside the column
	val contentColor = Color.White
	Box(
		modifier = Modifier
			.fillMaxSize(),
	) {
		val paddingCentered = 36.dp
		LibraryLikesTabComposable(
			contentColor = contentColor,
			paddingCentered = paddingCentered,
		)
		LibraryContainerCenterContent(
			attraction = attraction,
			themeColor = themeColor,
			contentColor = contentColor,
			paddingCentered = paddingCentered,
		)
	}
}