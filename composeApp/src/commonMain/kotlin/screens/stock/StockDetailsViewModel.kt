package screens.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repositories.MarketRepository
import data.repositories.PortfolioRepository
import data.repositories.StockRepository
import domain.models.PortfolioStockInfo
import domain.models.Share
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockDetailsViewModel(
    private val stockRepository: StockRepository,
    private val marketRepository: MarketRepository,
    private val portfolioRepository: PortfolioRepository,
    private val stockUid: String = ""
) : ViewModel() {

    private val _state = MutableStateFlow<StockDetailsState>(StockDetailsState.Loading)
    val state: StateFlow<StockDetailsState> = _state.asStateFlow()

    var chartType = ChartType.LINE
        set(value) {
            field = value
        }

    init {
        fetchStockDetails()
    }

    fun fetchStockDetails() {
        viewModelScope.launch {
            try {
                val share = stockRepository.getShareByUid(stockUid)
                val portfolio = portfolioRepository.getPortfolio()
                val stockInPortfolio = portfolio?.stocks?.firstOrNull { it.uid == stockUid }
                _state.value = StockDetailsState.Success(share!!, stockInPortfolio)
            } catch (e: Exception) {
                _state.value = StockDetailsState.Error(e.message ?: "An error occurred")
            }
        }
    }

}

sealed class StockDetailsState {
    object Loading : StockDetailsState()
    data class Success(val share: Share, val stockInfo: PortfolioStockInfo?) : StockDetailsState()
    data class Error(val message: String) : StockDetailsState()
}

enum class ChartType {
    CANDLES,
    LINE
}