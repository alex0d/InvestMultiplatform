package screens.tarot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repositories.TarotRepository
import domain.models.TarotPrediction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TarotViewModel(
    private val tarotRepository: TarotRepository,
    private val stockName: String = ""
) : ViewModel() {
    private val _state = MutableStateFlow<TarotPredictionState>(TarotPredictionState.Loading)
    val state: StateFlow<TarotPredictionState> = _state.asStateFlow()

    init {
        fetchTarotPrediction()
    }

    fun refreshTarotPrediction() {
        viewModelScope.launch {
            _state.value = TarotPredictionState.Loading

            val prediction = tarotRepository.refreshPrediction(stockName)

            if (prediction != null) {
                _state.value = TarotPredictionState.Success(prediction)
            } else {
                _state.value = TarotPredictionState.Error("An error occurred")
            }
        }
    }

    private fun fetchTarotPrediction() {
        viewModelScope.launch {
            val prediction = tarotRepository.getPrediction(stockName)

            if (prediction != null) {
                _state.value = TarotPredictionState.Success(prediction)
            } else {
                _state.value = TarotPredictionState.Error("An error occurred")
            }
        }
    }
}

sealed class TarotPredictionState {
    object Loading : TarotPredictionState()
    data class Success(val prediction: TarotPrediction) : TarotPredictionState()
    data class Error(val message: String) : TarotPredictionState()
}