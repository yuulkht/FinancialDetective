package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Category
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel

fun Category.toUi(): CategoryUiModel =
    CategoryUiModel(
        id = this.id,
        name = this.name,
        emoji = this.emoji,
        isIncome = this.isIncome
    )