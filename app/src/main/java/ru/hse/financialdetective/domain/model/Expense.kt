package ru.hse.financialdetective.domain.model

import java.time.Instant

data class Expense(
    val id: Int,
    val emoji: String,
    val category: String,
    val comment: String,
    val date: Instant,
    val amount: Double,
    val currency: Currency
)