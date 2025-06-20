package ru.hse.financialdetective.ui.uimodel.mapper

import java.time.format.DateTimeFormatter
import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import ru.hse.financialdetective.ui.uimodel.model.ExpenseUiModel
import ru.hse.financialdetective.ui.uimodel.model.ExpensesWithTotalUiModel
import java.time.ZoneId

fun Expense.toUi(): ExpenseUiModel {
    val zonedDateTime = this.date.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH.mm, dd.MM.yyyy")
    val formattedDate = zonedDateTime.format(formatter)

    return ExpenseUiModel(
        id = this.id,
        emoji = this.emoji,
        category = this.category,
        comment = this.comment,
        date = formattedDate,
        amount = this.amount.toString(),
        currency = this.currency
    )
}

fun ExpensesWithTotal.toUi(): ExpensesWithTotalUiModel =
    ExpensesWithTotalUiModel(
        expenses = this.items.map { it.toUi() },
        total = this.totalAmount.toString(),
        currency = this.currency
    )