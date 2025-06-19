package ru.hse.financialdetective.data.model

import java.time.Instant

data class AccountResponse(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
    val incomeStats: List<StateItem>,
    val expenseStats: List<StateItem>,
    val createdAt: Instant,
    val updatedAt: Instant
)