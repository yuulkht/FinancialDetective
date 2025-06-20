package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.domain.model.IncomesWithTotal
import ru.hse.financialdetective.ui.uimodel.model.IncomeUiModel
import ru.hse.financialdetective.ui.uimodel.model.IncomesWithTotalUiModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Income.toUi(): IncomeUiModel {
    val zonedDateTime = this.date.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy")
    val formattedDate = zonedDateTime.format(formatter)

    return IncomeUiModel(
        id = this.id,
        category = this.category,
        comment = this.comment,
        amount = this.amount.toString(),
        date = formattedDate,
        currency = getCurrencySymbol(this.currency)
    )
}


fun IncomesWithTotal.toUi(): IncomesWithTotalUiModel =
    IncomesWithTotalUiModel(
        incomes = this.items.map { it.toUi() },
        total = this.totalAmount.toString(),
        currency = this.currency
    )