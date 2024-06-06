package ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import investmultiplatform.composeapp.generated.resources.Res
import investmultiplatform.composeapp.generated.resources.account_circle
import investmultiplatform.composeapp.generated.resources.portfolio
import investmultiplatform.composeapp.generated.resources.profile
import investmultiplatform.composeapp.generated.resources.search
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBarItem
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun BottomBar() {
    NavigationBar {
        BottomBarItem.entries.forEach { barItem ->
            val selected = barItem == BottomBarItem.Portfolio

            AdaptiveNavigationBarItem(
                selected = selected,
                onClick = { },
                icon = {
                    Icon(
                        painter = painterResource(barItem.icon),
                        contentDescription = stringResource(barItem.label),
                        tint = if (selected) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurface
                    )
                },
                label = { Text(stringResource(barItem.label)) },
            )
        }
    }
}

enum class BottomBarItem(
//    val direction: DirectionDestinationSpec,
    val icon: DrawableResource,
    val label: StringResource
) {
    Portfolio(Res.drawable.portfolio, Res.string.portfolio),
    Search(Res.drawable.search, Res.string.search),
    Profile(Res.drawable.account_circle, Res.string.profile)
}