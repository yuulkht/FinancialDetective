package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class IncomeDetailedUiModel(
    val id: Int,
    val name: String,
    val amount: String,
    val date: String,
    val comment: String,
    val currency: String
)