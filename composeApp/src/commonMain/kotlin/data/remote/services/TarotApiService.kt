package data.remote.services

import data.remote.models.TarotPredictionDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TarotApiService(
    private val httpClient: HttpClient,
    private val investApiBaseUrl: String
) {
    suspend fun getPredictionByStockName(stockName: String): TarotPredictionDto {
        return httpClient.get("$investApiBaseUrl/api/tarot/$stockName").body()
    }
}