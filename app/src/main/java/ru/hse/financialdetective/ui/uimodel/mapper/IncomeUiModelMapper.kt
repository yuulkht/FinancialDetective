package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.domain.model.Incomes
import ru.hse.financialdetective.ui.uimodel.model.IncomeUiModel
import ru.hse.financialdetective.ui.uimodel.model.IncomesWithTotalUiModel

fun Income.toUi(): IncomeUiModel {

    return IncomeUiModel(
        id = this.id,
        category = this.category,
        comment = this.comment,
        amount = this.amount.toString(),
        date = convertInstantToDateWithTime(this.date),
        currency = this.currency.toUiModel()
    )
}


fun Incomes.toUi(): IncomesWithTotalUiModel =
    IncomesWithTotalUiModel(
        incomes = this.items.map { it.toUi() },
        total = this.items.sumOf { it.amount }.toString(),
        currency = this.currency.toUiModel()
    )