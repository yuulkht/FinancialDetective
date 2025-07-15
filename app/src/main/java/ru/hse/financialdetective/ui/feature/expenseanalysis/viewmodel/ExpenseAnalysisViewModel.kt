package ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.CalculateCategoryExpensesByExpenses
import ru.hse.financialdetective.domain.usecase.GetExpensesForPeriodUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.CategoriesForPieUiState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

class ExpenseAnalysisViewModel @Inject constructor(
    private val getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase,
    private val calculateCategoryExpensesByExpenses: CalculateCategoryExpensesByExpenses
) : ViewModel() {

    class Factory @Inject constructor(
        private val getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase,
        private val calculateCategoryExpensesByExpenses: CalculateCategoryExpensesByExpenses
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExpenseAnalysisViewModel(
                getExpensesForPeriodUseCase,
                calculateCategoryExpensesByExpenses
            ) as T
        }
    }

    private val _uiState =
        MutableStateFlow<CategoriesForPieUiState>(CategoriesForPieUiState.Loading)
    val uiState: StateFlow<CategoriesForPieUiState> = _uiState

    fun loadForPeriodExpenses(dateFrom: LocalDate, dateTo: LocalDate) {
        viewModelScope.launch {
            _uiState.value = CategoriesForPieUiState.Loading

            val result = getExpensesForPeriodUseCase(
                dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                dateTo.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()
            )
            _uiState.value = result.fold(
                onSuccess = { expenses ->

                    CategoriesForPieUiState.Success(calculateCategoryExpensesByExpenses(expenses).toUi())
                },
                onFailure = { error ->
                    CategoriesForPieUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }
}

