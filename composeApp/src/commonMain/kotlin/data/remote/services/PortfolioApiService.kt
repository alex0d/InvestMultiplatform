package data.remote.services

import data.remote.models.BuyStockRequest
import data.remote.models.PortfolioInfoDto
import data.remote.models.SellStockRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class PortfolioApiService(
    private val httpClient: HttpClient,
    private val investApiBaseUrl: String
) {
    suspend fun getPortfolio(): PortfolioInfoDto {
        return httpClient.get("$investApiBaseUrl/api/portfolio").body()
    }

    suspend fun buyStock(request: BuyStockRequest): String {
        return httpClient.post("$investApiBaseUrl/api/portfolio/buy") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun sellStock(request: SellStockRequest): String {
        return httpClient.post("$investApiBaseUrl/api/portfolio/sell") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}