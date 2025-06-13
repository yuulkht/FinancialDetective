package ru.hse.financialdetective.ui.uimodel.model

import androidx.compose.runtime.Immutable

@Immutable
data class CategoryUiModel(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)