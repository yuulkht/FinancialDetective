package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface IncomesUiState {
    data object Loading : IncomesUiState
    data class Success(val data: IncomesWithTotalUiModel) : IncomesUiState
    data class Error(val message: String) : IncomesUiState
}

@Immutable
data class IncomesWithTotalUiModel(
    val total: String,
    val currency: String,
    val incomes: List<IncomeUiModel>
)