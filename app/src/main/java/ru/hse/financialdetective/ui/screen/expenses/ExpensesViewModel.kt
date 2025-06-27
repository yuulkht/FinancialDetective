package ru.hse.financialdetective.ui.screen.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetExpensesTodayUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getExpensesTodayUseCase: GetExpensesTodayUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState: StateFlow<ExpensesUiState> = _uiState

    init {
        loadTodayExpenses()
    }

    private fun loadTodayExpenses() {
        viewModelScope.launch {
            _uiState.value = ExpensesUiState.Loading

            val result = getExpensesTodayUseCase()
            _uiState.value = result.fold(
                onSuccess = { expenses ->
                    ExpensesUiState.Success(expenses.toUi())
                },
                onFailure = { error ->
                    ExpensesUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }
}

