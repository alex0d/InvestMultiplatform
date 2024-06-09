package di

import data.repositories.*
import org.koin.dsl.module

//fun provideAppDatabase(context: Context): AppDatabase {
//    return Room.databaseBuilder(
//        context,
//        AppDatabase::class.java,
//        "app_database"
//    ).build()
//}

val dataModule = module {
//    single { provideAppDatabase(context = androidContext()) }

//    single { UserDataStore(context = androidContext()) }

    single { PortfolioRepository(portfolioApiService = get()) }

    single { StockRepository(stockApiService = get())}

    single { MarketRepository(marketApiService = get()) }

    single { UserRepository(apiService = get(), userDataSource = get()) }

//    single { TarotRepository(tarotApiService = get(), database = get()) }
    single { TarotRepository(tarotApiService = get()) }
}