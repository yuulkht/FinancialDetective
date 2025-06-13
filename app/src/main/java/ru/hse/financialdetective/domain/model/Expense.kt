package ru.hse.financialdetective.domain.model

data class Expense(
    val id: Int,
    val emoji: String,
    val category: String,
    val amount: Double,
    val currency: String
)