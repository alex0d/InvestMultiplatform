package screens.main

import MyIcons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import investmultiplatform.composeapp.generated.resources.Res
import investmultiplatform.composeapp.generated.resources.portfolio
import investmultiplatform.composeapp.generated.resources.profile
import investmultiplatform.composeapp.generated.resources.search
import myicons.AccountCircle
import myicons.Portfolio
import myicons.Search
import org.jetbrains.compose.resources.stringResource
import screens.portfolio.PortfolioScreen
import screens.profile.ProfileScreen
import screens.search.SearchScreen

sealed class BottomBarTab {
    object Portfolio : Tab {
        @Composable
        override fun Content() {
            PortfolioScreen().Content()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = stringResource(Res.string.portfolio)
                val icon = rememberVectorPainter(MyIcons.Portfolio)

                return TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon,
                )
            }
    }

    object Search : Tab {
        @Composable
        override fun Content() {
            SearchScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = stringResource(Res.string.search)
                val icon = rememberVectorPainter(MyIcons.Search)

                return TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon,
                )
            }
    }

    object Profile : Tab {
        @Composable
        override fun Content() {
            ProfileScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = stringResource(Res.string.profile)
                val icon = rememberVectorPainter(MyIcons.AccountCircle)

                return TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon,
                )
            }
    }
}