package data.remote.services

import data.remote.models.ShareDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class StockApiService(
    private val httpClient: HttpClient,
    private val investApiBaseUrl: String
) {
    suspend fun getSharesByTicker(ticker: String): List<ShareDto> {
        return httpClient.get("$investApiBaseUrl/api/search/shares/$ticker").body()
    }

    suspend fun getShareByUid(uid: String): ShareDto {
        return httpClient.get("$investApiBaseUrl/api/share/$uid").body()
    }
}