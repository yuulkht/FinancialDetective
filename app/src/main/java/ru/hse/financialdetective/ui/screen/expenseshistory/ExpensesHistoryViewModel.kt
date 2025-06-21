package ru.hse.financialdetective.ui.screen.expenseshistory

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.domain.usecase.GetExpensesForPeriodUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ExpensesHistoryViewModel @Inject constructor(
    private val getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState: StateFlow<ExpensesUiState> = _uiState

    val dateFrom = mutableStateOf(LocalDate.now().withDayOfMonth(1))
    val dateTo = mutableStateOf(LocalDate.now())

    fun loadForPeriodExpenses() {
        viewModelScope.launch {
            _uiState.value = ExpensesUiState.Loading

            val result = getExpensesForPeriodUseCase(dateFrom.value, dateTo.value)
            _uiState.value = result.fold(
                onSuccess = { expenses ->
                    ExpensesUiState.Success(expenses.toUi())
                },
                onFailure = { error ->
                    ExpensesUiState.Error(error.message ?: "Неизвестная ошибка")
                }
            )
        }
    }
}

