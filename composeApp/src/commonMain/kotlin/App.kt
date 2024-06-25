import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.auth.AuthScreen
import ui.theme.InvestAppTheme

@Composable
@Preview
fun App() {
    InvestAppTheme {
        Navigator(
            screen = AuthScreen(),
//            disposeBehavior = NavigatorDisposeBehavior(disposeNestedNavigators = false)
        )
    }
}
