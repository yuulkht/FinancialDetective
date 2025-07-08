package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class ExpenseUiModel(
    val id: Int,
    val emoji: String,
    val category: String,
    val comment: String,
    val date: String,
    val amount: String,
    val currency: CurrencyUiModel
)