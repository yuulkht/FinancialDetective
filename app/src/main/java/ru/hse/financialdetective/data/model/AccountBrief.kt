package ru.hse.financialdetective.data.model

data class AccountBrief(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String
)