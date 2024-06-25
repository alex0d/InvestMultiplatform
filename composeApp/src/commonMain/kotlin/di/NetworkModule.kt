package di

import data.local.UserDataSource
import data.remote.models.AuthResponse
import data.remote.models.RefreshRequest
import data.remote.services.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val investApiBaseUrl = "https://invest.alex0d.ru"  // TODO: replace with BuildConfig

fun provideHttpClient(userDataSource: UserDataSource): HttpClient {
    return HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger =
                object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
        }

        install(Auth) {
            bearer {
                loadTokens {
                    println("loadTokens")
                    BearerTokens(
                        accessToken = userDataSource.accessToken.first() ?: "",
                        refreshToken = userDataSource.refreshToken.first() ?: ""
                    ).also {
                        println("loadTokens: ${it.accessToken} ${it.refreshToken}")
                    }
                }

                refreshTokens {
                    println("refreshTokens")
                    val refreshRequest = RefreshRequest(
                        refreshToken = userDataSource.refreshToken.first() ?: ""
                    )

                    client.post("$investApiBaseUrl/api/auth/refresh") {
                        markAsRefreshTokenRequest()
                        contentType(ContentType.Application.Json)
                        setBody(refreshRequest)
                    }.body<AuthResponse>().let {

                        userDataSource.saveAccessToken(it.accessToken)
                        userDataSource.saveRefreshToken(it.refreshToken)

                        BearerTokens(
                            accessToken = it.accessToken,
                            refreshToken = it.refreshToken
                        )
                    }
                }
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
