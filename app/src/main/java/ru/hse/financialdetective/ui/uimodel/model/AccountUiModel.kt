package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class AccountUiModel(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)