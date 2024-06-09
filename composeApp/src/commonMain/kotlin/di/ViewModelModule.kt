package di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import screens.auth.AuthViewModel
import screens.order.OrderViewModel
import screens.portfolio.PortfolioViewModel
import screens.profile.ProfileViewModel
import screens.search.SearchViewModel
import screens.stock.StockDetailsViewModel
import screens.tarot.TarotViewModel

val viewModelModule = module {
    viewModel { PortfolioViewModel(portfolioRepository = get()) }

    viewModel {
        AuthViewModel(userRepository = get())
    }

    viewModel { SearchViewModel(stockRepository = get()) }

    viewModel { ProfileViewModel(userRepository = get()) }

    viewModel { parameters ->
        StockDetailsViewModel(
            stockRepository = get(),
            marketRepository = get(),
            portfolioRepository = get(),
            stockUid = parameters.get()
        )
    }

    viewModel { parameters ->
        OrderViewModel(
            stockRepository = get(),
            portfolioRepository = get(),
            orderAction = parameters.get(),
            stockUid = parameters.get()
        )
    }

    viewModel { parameters ->
        TarotViewModel(
            tarotRepository = get(),
            stockName = parameters.get()
        )
    }
}