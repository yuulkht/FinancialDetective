package ru.hse.financialdetective.domain.model

import java.time.Instant

data class CreateTransactionRequest(
    val categoryId: Int,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?
)