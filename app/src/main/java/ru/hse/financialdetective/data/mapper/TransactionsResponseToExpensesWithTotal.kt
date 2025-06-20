package ru.hse.financialdetective.data.mapper

import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.ExpensesWithTotal

fun TransactionsResponse.toExpensesDomain(): ExpensesWithTotal {
    val expenses = transactions
        .filter { !it.categoryDto.isIncome }
        .map { transaction ->
            Expense(
                id = transaction.id,
                emoji = transaction.categoryDto.emoji,
                category = transaction.categoryDto.name,
                comment = transaction.comment ?: "",
                date = transaction.transactionDate,
                amount = transaction.amount.toDouble(),
                currency = transaction.account.currency
            )
        }
        .sortedByDescending { it.date }

    return ExpensesWithTotal(expenses)
}