package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface CreateTransactionUiState {
    data object Loading : CreateTransactionUiState
    data class Success(val data: CreateTransactionUiModel) : CreateTransactionUiState
    data class Error(val message: String) : CreateTransactionUiState
}

@Immutable
data class CreateTransactionUiModel(
    val account: AccountUiModel,
    val category: CategoryUiModel?,
    val amount: String,
    val date: String,
    val time: String,
    val comment: String,
    val categories: List<CategoryUiModel>
)