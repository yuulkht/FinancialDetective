package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Categories
import ru.hse.financialdetective.domain.model.Category
import ru.hse.financialdetective.ui.uimodel.model.CategoriesUiModel
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel

fun Category.toUi(): CategoryUiModel {

    return CategoryUiModel(
        id = this.id,
        emoji = this.emoji,
        name = this.name,
        isIncome = this.isIncome
    )
}

fun Categories.toUi(): CategoriesUiModel =
    CategoriesUiModel(
        categories = this.items.map { it.toUi() }
    )

fun CategoryUiModel.toDomain(): Category {
    return Category(
        id = this.id,
        emoji = this.emoji,
        name = this.name,
        isIncome = this.isIncome
    )
}

fun CategoriesUiModel.toDomain(): Categories {
    return Categories(
        items = this.categories.map { it.toDomain() }
    )
}