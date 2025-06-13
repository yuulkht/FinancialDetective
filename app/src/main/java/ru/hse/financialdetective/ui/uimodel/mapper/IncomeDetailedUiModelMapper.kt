package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.IncomeDetailed
import ru.hse.financialdetective.ui.uimodel.model.IncomeDetailedUiModel

fun IncomeDetailed.toUi(): IncomeDetailedUiModel =
    IncomeDetailedUiModel(
        id = this.id,
        name = this.name,
        amount = this.amount.toString(),
        date = this.date,
        comment = this.comment,
        currency = this.currency
    )