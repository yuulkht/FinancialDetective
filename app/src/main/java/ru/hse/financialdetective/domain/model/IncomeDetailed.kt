package ru.hse.financialdetective.domain.model

data class IncomeDetailed(
    val id: Int,
    val name: String,
    val amount: Double,
    val date: String,
    val comment: String,
    val currency: String
)