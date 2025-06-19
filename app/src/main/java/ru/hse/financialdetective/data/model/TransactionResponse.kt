package ru.hse.financialdetective.data.model

import java.time.Instant

data class TransactionResponse(
    val id: Int,
    val account: AccountBrief,
    val category: Category,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant
)