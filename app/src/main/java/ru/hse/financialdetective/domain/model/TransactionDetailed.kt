package ru.hse.financialdetective.domain.model

import java.time.Instant

data class TransactionDetailed(
    val id: Int,
    val account: Account,
    val emoji: String,
    val category: Category,
    val amount: Double,
    val date: Instant,
    val comment: String,
    val currency: Currency
)