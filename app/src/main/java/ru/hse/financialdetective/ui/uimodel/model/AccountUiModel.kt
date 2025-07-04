package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

sealed interface AccountUiState {
    data object Loading : AccountUiState
    data class Success(val data: AccountUiModel) : AccountUiState
    data class Error(val message: String) : AccountUiState
}

@Immutable
data class AccountUiModel(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: CurrencyUiModel
)