package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.CategoriesTransactions
import ru.hse.financialdetective.domain.model.CategoryTransaction
import ru.hse.financialdetective.ui.uimodel.model.CategoriesForPieUiModel
import ru.hse.financialdetective.ui.uimodel.model.CategoryForPieChartUiModel

fun CategoryTransaction.toUi(): CategoryForPieChartUiModel {

    return CategoryForPieChartUiModel(
        emoji = this.emoji,
        name = this.name,
        amount = this.amount.toString(),
        percent = this.percent,
        currency = this.currency.toUiModel()
    )
}

fun CategoriesTransactions.toUi(): CategoriesForPieUiModel =
    CategoriesForPieUiModel(
        categories = this.categoryTransactions.map { it.toUi() },
        total = this.total.toString(),
        currency = this.currency.toUiModel()
    )