package ru.hse.financialdetective.domain.model

data class UpdateAccountRequest(
    val name: String,
    val balance: String,
    val currency: String
)