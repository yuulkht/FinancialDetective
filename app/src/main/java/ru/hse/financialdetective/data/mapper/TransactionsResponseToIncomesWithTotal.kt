package ru.hse.financialdetective.data.mapper

import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.ExpensesWithTotal

fun TransactionsResponse.toExpensesDomain(): ExpensesWithTotal {
    val expenses: MutableList<Expense> = mutableListOf()
    for (transaction: TransactionResponse in this.transactions) {
        if (!transaction.categoryDto.isIncome) {
            expenses.add(Expense(
                id = transaction.id,
                emoji = transaction.categoryDto.emoji,
                category = transaction.categoryDto.name,
                amount = transaction.amount.toDouble(),
                currency = transaction.account.currency
            ))
        }
    }

    return ExpensesWithTotal(expenses)
}