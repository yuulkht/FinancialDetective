package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.Expenses
import ru.hse.financialdetective.domain.model.TransactionAnalysis
import ru.hse.financialdetective.domain.model.TransactionsAnalysis
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
        currency = this.currency.toUiModel()
    )
}

fun Expenses.toUi(): ExpensesWithTotalUiModel =
    ExpensesWithTotalUiModel(
        expenses = this.items.map { it.toUi() },
        total = this.items.sumOf { it.amount }.toString(),
        currency = this.currency.toUiModel()
    )


fun TransactionAnalysis.toExpenseUi(): ExpenseUiModel {

    return ExpenseUiModel(
        id = this.id,
        emoji = this.emoji,
        category = this.category,
        comment = this.comment,
        date = convertInstantToDateWithTime(this.date),
        amount = this.amount.toString(),
        currency = this.currency.toUiModel()
    )
}

fun TransactionsAnalysis.toExpensesUi(): ExpensesWithTotalUiModel =
    ExpensesWithTotalUiModel(
        expenses = this.items.map { it.toExpenseUi() },
        total = this.items.sumOf { it.amount }.toString(),
        currency = this.currency.toUiModel()
    )