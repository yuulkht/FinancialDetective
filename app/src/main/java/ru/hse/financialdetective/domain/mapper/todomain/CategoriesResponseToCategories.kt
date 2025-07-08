package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.CategoriesResponse
import ru.hse.financialdetective.domain.model.Categories
import ru.hse.financialdetective.domain.model.Category

fun CategoriesResponse.toDomain(): Categories {
    val categories = this.categories
        .map { category ->
            Category(
                id = category.id,
                emoji = category.emoji,
                name = category.name,
                isIncome = category.isIncome
            )
        }

    return Categories(categories)
}