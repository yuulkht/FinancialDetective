package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class ExpensesWithTotalUiModel(
    val total: String,
    val currency: String,
    val expenses: List<ExpenseUiModel>
)