package ru.hse.financialdetective.domain.model

data class CategoryTransaction(
    val emoji: String,
    val name: String,
    val amount: Double,
    val percent: Float,
    val currency: Currency
)

data class CategoriesTransactions(
    val categoryTransactions: List<CategoryTransaction>,
    val total: Double,
    val currency: Currency
)