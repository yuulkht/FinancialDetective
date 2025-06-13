package ru.hse.financialdetective.domain.model

data class ExpenseDetailed(
    val id: Int,
    val emoji: String,
    val category: String,
    val amount: Double,
    val date: String,
    val comment: String,
    val currency: String
)