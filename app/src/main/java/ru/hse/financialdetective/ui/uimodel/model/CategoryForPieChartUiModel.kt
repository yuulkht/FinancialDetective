package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface CategoriesForPieUiState {
    data object Loading : CategoriesForPieUiState
    data class Success(val data: CategoriesForPieUiModel) : CategoriesForPieUiState
    data class Error(val message: String) : CategoriesForPieUiState
}

@Immutable
data class CategoryForPieChartUiModel(
    val emoji: String,
    val name: String,
    val amount: String,
    val percent: Float,
    val currency: CurrencyUiModel
)

@Immutable
data class CategoriesForPieUiModel(
    val total: String,
    val currency: CurrencyUiModel,
    val categories: List<CategoryForPieChartUiModel>
)