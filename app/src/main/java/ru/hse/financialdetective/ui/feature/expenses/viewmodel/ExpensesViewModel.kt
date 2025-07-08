package ru.hse.financialdetective.ui.feature.expenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.domain.usecase.GetExpensesTodayUseCase
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState
import javax.inject.Inject

class ExpensesViewModel @Inject constructor(
    private val getExpensesTodayUseCase: GetExpensesTodayUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getExpensesTodayUseCase: GetExpensesTodayUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExpensesViewModel(getExpensesTodayUseCase) as T
        }
    }

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

