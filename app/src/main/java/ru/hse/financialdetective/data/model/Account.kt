package ru.hse.financialdetective.data.model

import java.time.Instant

data class Account(
    val id: Int,
    val userId: Int,
    val name: String,
    val balance: String,
    val currency: String,
    val createdAt: Instant,
    val updatedAt: Instant
)

data class AccountsResponse(
    val accounts: List<Account>
)
