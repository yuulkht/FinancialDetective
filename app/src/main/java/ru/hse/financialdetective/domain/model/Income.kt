package ru.hse.financialdetective.domain.model

data class Income(
    val id: Int,
    val name: String,
    val amount: String,
    val currency: String
)