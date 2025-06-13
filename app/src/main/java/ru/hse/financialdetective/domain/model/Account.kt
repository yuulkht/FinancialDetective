package ru.hse.financialdetective.domain.model

data class Account(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)