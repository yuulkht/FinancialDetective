package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class IncomeUiModel(
    val id: Int,
    val category: String,
    val comment: String,
    val date: String,
    val amount: String,
    val currency: String
)