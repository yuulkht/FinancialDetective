package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Currency
import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.domain.model.Expenses

fun TransactionsResponse.toExpensesDomain(currency: Currency): Expenses {
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
                currency = transaction.account.currency.toCurrencyDomain()
            )
        }
        .sortedByDescending { it.date }

    return Expenses(expenses, currency)
}