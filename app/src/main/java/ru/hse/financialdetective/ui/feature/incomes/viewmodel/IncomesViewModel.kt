package ru.hse.financialdetective.ui.feature.incomes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetIncomesTodayUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.IncomesUiState
import javax.inject.Inject

class IncomesViewModel @Inject constructor(
    private val getIncomesTodayUseCase: GetIncomesTodayUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getIncomesTodayUseCase: GetIncomesTodayUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return IncomesViewModel(getIncomesTodayUseCase) as T
        }
    }

    private val _uiState = MutableStateFlow<IncomesUiState>(IncomesUiState.Loading)
    val uiState: StateFlow<IncomesUiState> = _uiState

    init {
        loadTodayIncomes()
    }

    private fun loadTodayIncomes() {
        viewModelScope.launch {
            _uiState.value = IncomesUiState.Loading

            val result = getIncomesTodayUseCase()
            _uiState.value = result.fold(
                onSuccess = { incomes ->
                    IncomesUiState.Success(incomes.toUi())
                },
                onFailure = { error ->
                    IncomesUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }
}

