package ru.hse.financialdetective.domain.model

import java.time.Instant

data class TransactionDetailed(
    val id: Int,
    val emoji: String,
    val category: String,
    val amount: Double,
    val date: Instant,
    val comment: String,
    val currency: Currency
)