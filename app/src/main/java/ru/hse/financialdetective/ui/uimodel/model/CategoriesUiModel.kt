package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface CategoriesUiState {
    data object Loading : CategoriesUiState
    data class Success(val data: CategoriesUiModel) : CategoriesUiState
    data class Error(val message: String) : CategoriesUiState
}

@Immutable
data class CategoriesUiModel(
    val categories: List<CategoryUiModel>
)