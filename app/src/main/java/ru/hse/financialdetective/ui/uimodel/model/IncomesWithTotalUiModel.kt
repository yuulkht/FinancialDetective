package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class IncomesWithTotalUiModel(
    val total: String,
    val currency: String,
    val incomes: List<IncomeUiModel>
)