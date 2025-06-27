package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import ru.hse.financialdetective.ui.uimodel.model.ExpenseUiModel
import ru.hse.financialdetective.ui.uimodel.model.ExpensesWithTotalUiModel

fun Expense.toUi(): ExpenseUiModel {

    return ExpenseUiModel(
        id = this.id,
        emoji = this.emoji,
        category = this.category,
        comment = this.comment,
        date = convertInstantToDateWithTime(this.date),
        amount = this.amount.toString(),
        currency = getCurrencySymbol(this.currency)
    )
}

fun ExpensesWithTotal.toUi(): ExpensesWithTotalUiModel =
    ExpensesWithTotalUiModel(
        expenses = this.items.map { it.toUi() },
        total = this.totalAmount.toString(),
        currency = this.currency
    )