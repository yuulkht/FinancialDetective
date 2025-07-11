package ru.hse.financialdetective.domain.model

import java.time.Instant

data class Transaction(
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: Double,
    val date: Instant,
    val comment: String,
    val createdAt: Instant,
    val updatedAt: Instant
)