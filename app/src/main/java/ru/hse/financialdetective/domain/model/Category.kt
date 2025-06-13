package ru.hse.financialdetective.domain.model

data class Category(
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)