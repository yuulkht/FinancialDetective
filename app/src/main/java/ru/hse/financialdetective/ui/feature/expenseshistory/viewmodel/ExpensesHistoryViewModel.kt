package ru.hse.financialdetective.ui.feature.expenseshistory.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetExpensesForPeriodUseCase
import ru.hse.financialdetective.domain.usecase.GetIncomesTodayUseCase
import ru.hse.financialdetective.ui.feature.incomes.viewmodel.IncomesViewModel
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

class ExpensesHistoryViewModel @Inject constructor(
    private val getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExpensesHistoryViewModel(getExpensesForPeriodUseCase) as T
        }
    }

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState: StateFlow<ExpensesUiState> = _uiState

    val dateFrom = mutableStateOf(LocalDate.now().withDayOfMonth(1))
    val dateTo = mutableStateOf(LocalDate.now())

    init {
        loadForPeriodExpenses()
    }

    fun loadForPeriodExpenses() {
        viewModelScope.launch {
            _uiState.value = ExpensesUiState.Loading

            val result = getExpensesForPeriodUseCase(
                dateFrom.value.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                dateTo.value.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()
            )
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

