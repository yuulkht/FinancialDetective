package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.ExpenseDetailed
import ru.hse.financialdetective.ui.uimodel.model.ExpenseDetailedUiModel

fun ExpenseDetailed.toUi(): ExpenseDetailedUiModel {

    return ExpenseDetailedUiModel(
        id = this.id,
        emoji = this.emoji,
        category = this.currency,
        amount = this.amount.toString(),
        date = convertInstantToDateWithTime(this.date),
        comment = this.comment,
        currency = getCurrencySymbol(this.currency)
    )
}
