package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface ExpensesUiState {
    object Loading : ExpensesUiState
    data class Success(val data: ExpensesWithTotalUiModel) : ExpensesUiState
    data class Error(val message: String) : ExpensesUiState
}

@Immutable
data class ExpensesWithTotalUiModel(
    val total: String,
    val currency: String,
    val expenses: List<ExpenseUiModel>
)