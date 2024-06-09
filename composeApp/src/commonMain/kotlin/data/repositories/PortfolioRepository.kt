package data.repositories

import data.mappers.toPortfolioInfo
import data.remote.models.BuyStockRequest
import data.remote.models.SellStockRequest
import data.remote.services.PortfolioApiService
import domain.models.PortfolioInfo

class PortfolioRepository(private val portfolioApiService: PortfolioApiService) {

    suspend fun getPortfolio(): PortfolioInfo? {
        return try {
            val portfolio = portfolioApiService.getPortfolio()
            portfolio.toPortfolioInfo()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun buyStock(uid: String, amount: Int): Boolean {
        val request = BuyStockRequest(uid, amount)

        val response = try {
            portfolioApiService.buyStock(request)
        } catch (e: Exception) {
            return false
        }

//        return response.isSuccessful && response.body().toString() == "Stock bought"
        return response == "Stock bought"
    }

    suspend fun sellStock(uid: String, amount: Int): Boolean {
        val request = SellStockRequest(uid, amount)

        val response = try {
            portfolioApiService.sellStock(request)
        } catch (e: Exception) {
            return false
        }

//        return response.isSuccessful && response.body().toString() == "Stock sold"
        return response == "Stock sold"
    }
}