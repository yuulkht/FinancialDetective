package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.CategoriesResponse
import ru.hse.financialdetective.data.model.CategoryDto
import ru.hse.financialdetective.domain.model.Categories
import ru.hse.financialdetective.domain.model.Category

fun CategoriesResponse.toDomain(): Categories {
    val categories = this.categories
        .map { it.toDomain() }

    return Categories(categories)
}

fun CategoryDto.toDomain(): Category =
    Category(
        id = this.id,
        emoji = this.emoji,
        name = this.name,
        isIncome = this.isIncome
    )
