package ru.hse.financialdetective.domain.model

data class CategoryExpense(
    val emoji: String,
    val name: String,
    val amount: Double,
    val percent: Float,
    val currency: Currency
)

data class CategoriesExpenses(
    val categoryExpenses: List<CategoryExpense>,
    val total: Double,
    val currency: Currency
)