package ru.hse.financialdetective.domain.model

data class Incomes(
    val items: List<Income>,
    val currency: Currency
)
