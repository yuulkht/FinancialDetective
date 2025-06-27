package ru.hse.financialdetective.domain.model

import java.time.Instant

data class IncomeDetailed(
    val id: Int,
    val name: String,
    val amount: Double,
    val date: Instant,
    val comment: String,
    val currency: String
)