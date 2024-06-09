import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import di.nativeModule
import di.startDI
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    startDI(nativeModule) {  }

    ComposeViewport(document.body!!) {
        App()
    }
}