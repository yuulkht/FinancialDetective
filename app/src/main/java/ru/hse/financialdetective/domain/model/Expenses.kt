package ru.hse.financialdetective.domain.model

data class Expenses(
    val items: List<Expense>,
    val currency: Currency,
)
