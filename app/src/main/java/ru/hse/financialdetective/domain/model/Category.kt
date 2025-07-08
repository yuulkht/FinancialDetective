package ru.hse.financialdetective.domain.model

data class Category(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)

data class Categories(
    val items: List<Category>
)