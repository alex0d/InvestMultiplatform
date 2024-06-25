package screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

class MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(BottomBarTab.Portfolio) {
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(BottomBarTab.Portfolio)
                        TabNavigationItem(BottomBarTab.Search)
                        TabNavigationItem(BottomBarTab.Profile)
                    }
                }
            ) { innerPadding ->
                Box(
                   modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                ) {
                    CurrentTab()
                }
            }
        }

    }

}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current.key == tab.key

    NavigationBarItem(
        selected = selected,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title,
                tint = if (selected) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurface
            )
        },
        label = { Text(tab.options.title) },
    )
}