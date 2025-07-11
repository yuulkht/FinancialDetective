package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface TransactionUiState {
    data object Loading : TransactionUiState
    data class Success(val data: TransactionUiModel) : TransactionUiState
    data class Error(val message: String) : TransactionUiState
}

@Immutable
data class TransactionUiModel(
    val id: Int,
    val account: AccountUiModel,
    val category: CategoryUiModel,
    val amount: String,
    val date: String,
    val time: String,
    val comment: String,
    val categories: List<CategoryUiModel>
)