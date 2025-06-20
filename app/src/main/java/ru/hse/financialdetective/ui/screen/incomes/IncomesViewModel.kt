package ru.hse.financialdetective.ui.screen.incomes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.domain.usecase.GetIncomesTodayUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.IncomesUiState
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getIncomesTodayUseCase: GetIncomesTodayUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<IncomesUiState>(IncomesUiState.Loading)
    val uiState: StateFlow<IncomesUiState> = _uiState

    fun loadTodayIncomes() {
        viewModelScope.launch {
            _uiState.value = IncomesUiState.Loading

            val result = getIncomesTodayUseCase()
            _uiState.value = result.fold(
                onSuccess = { incomes ->
                    IncomesUiState.Success(incomes.toUi())
                },
                onFailure = { error ->
                    IncomesUiState.Error(error.message ?: "Неизвестная ошибка")
                }
            )
        }
    }
}

