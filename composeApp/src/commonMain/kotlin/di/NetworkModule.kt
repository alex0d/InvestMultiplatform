package di

import data.local.UserDataSource
import data.remote.services.*
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val investApiBaseUrl = "https://invest.alex0d.ru"  // TODO: replace with BuildConfig

fun provideHttpClient(userDataSource: UserDataSource): HttpClient {
    return HttpClient {
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJBbGVrc2V5IiwibGFzdG5hbWUiOiJEZW5pc292IiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6ImFsZXhAbWFpbC5ydSIsImlhdCI6MTcxNzkzODY2NCwiZXhwIjoxNzE3OTQwNDY0fQ.F4P0nQ783JtMSOvDUn1VCwh8xC_j2vpKwEXjFNwBqBA",
                        refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTc5Mzg2NjQsImV4cCI6MTcyMDUzMDY2NH0.hy1zZiqmJiwocM0ZEdYmTaLMLencnMwTbi-cphg8W0U"
                    )
                }

//                refreshTokens {
//                    val refreshRequest = RefreshRequest(
//                        refreshToken = userDataSource.refreshToken.first() ?: ""
//                    )
//
//                    client.get("$investApiBaseUrl/api/auth/refresh") {
//                        markAsRefreshTokenRequest()
//                        contentType(ContentType.Application.Json)
//                        setBody(refreshRequest)
//                    }.body<AuthResponse>().let {
//
//                        userDataSource.saveAccessToken(it.accessToken)
//                        userDataSource.saveRefreshToken(it.refreshToken)
//
//                        BearerTokens(
//                            accessToken = it.accessToken,
//                            refreshToken = it.refreshToken
//                        )
//                    }
//                }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
        }
    }
}

val networkModule = module {
    single { provideHttpClient(userDataSource = get()) }

    single { AuthApiService(httpClient = get(), investApiBaseUrl = investApiBaseUrl) }

    single { MarketApiService(httpClient = get(), investApiBaseUrl = investApiBaseUrl) }

    single { PortfolioApiService(httpClient = get(), investApiBaseUrl = investApiBaseUrl) }

    single { StockApiService(httpClient = get(), investApiBaseUrl = investApiBaseUrl) }

    single { TarotApiService(httpClient = get(), investApiBaseUrl = investApiBaseUrl) }
}
