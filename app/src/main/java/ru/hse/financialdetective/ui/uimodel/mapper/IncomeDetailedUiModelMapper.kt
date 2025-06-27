package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.IncomeDetailed
import ru.hse.financialdetective.ui.uimodel.model.IncomeDetailedUiModel

fun IncomeDetailed.toUi(): IncomeDetailedUiModel {

    return IncomeDetailedUiModel(
        id = this.id,
        name = this.name,
        amount = this.amount.toString(),
        date = convertInstantToDateWithTime(this.date),
        comment = this.comment,
        currency = getCurrencySymbol(this.currency)
    )
}
