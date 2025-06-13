package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class ExpenseDetailedUiModel(
    val id: Int,
    val emoji: String,
    val category: String,
    val amount: String,
    val date: String,
    val comment: String,
    val currency: String
)