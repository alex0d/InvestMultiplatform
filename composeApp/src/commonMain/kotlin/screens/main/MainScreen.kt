package screens.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

@Composable
fun MainScreen(
//    rootNavigator: DestinationsNavigator
) {

//    Scaffold(bottomBar = {
//        BottomBar(navController = navController)
//    }) { innerPadding ->
//        DestinationsNavHost(
//            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
//            navGraph = NavGraphs.main,
//            navController = navController
//        ) {
//            composable(ProfileScreenDestination) {
//                ProfileScreen(
//                    rootNavigator = rootNavigator
//                )
//            }
//        }
//    }

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
            CurrentTab()
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
        label = { Text(tab.options.title, color = MaterialTheme.colorScheme.onPrimary) },
    )
}