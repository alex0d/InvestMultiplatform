package data.repositories

import data.mappers.toShare
import data.remote.services.StockApiService
import domain.models.Share

class StockRepository(private val stockApiService: StockApiService) {

    suspend fun getSharesByTicker(ticker: String): List<Share>? {
        return try {
            val shares = stockApiService.getSharesByTicker(ticker)
            shares.map { it.toShare() }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getShareByUid(uid: String): Share? {
        return try {
            val share = stockApiService.getShareByUid(uid)
            share.toShare()
        } catch (e: Exception) {
            null
        }
    }
}
