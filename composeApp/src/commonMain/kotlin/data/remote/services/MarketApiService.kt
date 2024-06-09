package data.remote.services

import data.remote.models.CandleDto
import data.remote.models.CandleRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MarketApiService(
    private val httpClient: HttpClient,
    private val investApiBaseUrl: String
) {
    suspend fun getCandles(request: CandleRequest): List<CandleDto> {
        return httpClient.post("$investApiBaseUrl/api/market/candles") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}