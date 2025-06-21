package ru.hse.financialdetective.domain.model

import java.time.Instant

data class Income(
    val id: Int,
    val category: String,
    val comment: String,
    val date: Instant,
    val amount: Double,
    val currency: String
)