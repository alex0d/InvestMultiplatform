package data.repositories

import data.mappers.toTarotPrediction
import data.remote.services.TarotApiService
import domain.models.TarotPrediction

class TarotRepository(
    private val tarotApiService: TarotApiService,
//    private val database: AppDatabase
) {

    suspend fun getPrediction(stockName: String): TarotPrediction? {
//        val prediction = getFromDatabase(stockName)
//        if (prediction != null) {
//            return prediction.toTarotPrediction()
//        }
        return getFromApi(stockName)
    }

    suspend fun refreshPrediction(stockName: String): TarotPrediction? {
//        database.tarotPredictionDao().delete(stockName)
        return getFromApi(stockName)
    }

    private suspend fun getFromApi(stockName: String): TarotPrediction? {
        val prediction = try {
            tarotApiService.getPredictionByStockName(stockName)
        } catch (e: Exception) {
            return null
        }

//        database.tarotPredictionDao().insert(
//            prediction.toTarotPredictionDbo(stockName)
//        )

        return prediction.toTarotPrediction()
    }

//    private suspend fun getFromDatabase(stockName: String) =
//        database.tarotPredictionDao().getByStockName(stockName)

}