package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.domain.model.IncomesWithTotal
import ru.hse.financialdetective.ui.uimodel.model.IncomeUiModel
import ru.hse.financialdetective.ui.uimodel.model.IncomesWithTotalUiModel

fun Income.toUi(): IncomeUiModel {

    return IncomeUiModel(
        id = this.id,
        category = this.category,
        comment = this.comment,
        amount = this.amount.toString(),
        date = convertInstantToDateWithTime(this.date),
        currency = getCurrencySymbol(this.currency)
    )
}


fun IncomesWithTotal.toUi(): IncomesWithTotalUiModel =
    IncomesWithTotalUiModel(
        incomes = this.items.map { it.toUi() },
        total = this.totalAmount.toString(),
        currency = this.currency
    )