import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.nativeModule
import di.startDI

fun main() = application {

    startDI(nativeModule)

    Window(
        onCloseRequest = ::exitApplication,
        title = "InvestMultiplatform",
    ) {
        App()
    }
}