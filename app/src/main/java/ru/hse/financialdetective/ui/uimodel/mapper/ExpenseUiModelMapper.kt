package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.ExpenseWithTotal
import ru.hse.financialdetective.ui.uimodel.model.ExpenseUiModel
import ru.hse.financialdetective.ui.uimodel.model.ExpensesWithTotalUiModel

fun Expense.toUi(): ExpenseUiModel =
    ExpenseUiModel(
        id = this.id,
        emoji = this.emoji,
        category = this.category,
        amount = this.amount.toString(),
        currency = this.currency
    )

fun ExpenseWithTotal.toUi(): ExpensesWithTotalUiModel =
    ExpensesWithTotalUiModel(
        expenses = this.items.map { it.toUi() },
        total = this.totalAmount.toString(),
        currency = this.currency
    )