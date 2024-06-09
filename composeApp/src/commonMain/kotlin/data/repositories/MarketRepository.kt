package data.repositories

import data.mappers.toCandle
import data.remote.models.CandleRequest
import data.remote.services.MarketApiService
import domain.models.Candle
import domain.models.CandleInterval
import domain.models.toApiString

class MarketRepository(private val marketApiService: MarketApiService) {

    suspend fun getCandles(uid: String, from: Long, to: Long, interval: CandleInterval): List<Candle>? {
        return try {
            val request = CandleRequest(
                uid = uid,
                from = from,
                to = to,
                interval = interval.toApiString()
            )
            val candles = marketApiService.getCandles(request)
            candles.map { it.toCandle() }
        } catch (e: Exception) {
            null
        }
    }

}