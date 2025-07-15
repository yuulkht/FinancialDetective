package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.CategoriesExpenses
import ru.hse.financialdetective.domain.model.CategoryExpense
import ru.hse.financialdetective.ui.uimodel.model.CategoriesForPieUiModel
import ru.hse.financialdetective.ui.uimodel.model.CategoryForPieChartUiModel

fun CategoryExpense.toUi(): CategoryForPieChartUiModel {

    return CategoryForPieChartUiModel(
        emoji = this.emoji,
        name = this.name,
        amount = this.amount.toString(),
        percent = this.percent,
        currency = this.currency.toUiModel()
    )
}

fun CategoriesExpenses.toUi(): CategoriesForPieUiModel =
    CategoriesForPieUiModel(
        categories = this.categoryExpenses.map { it.toUi() },
        total = this.total.toString(),
        currency = this.currency.toUiModel()
    )