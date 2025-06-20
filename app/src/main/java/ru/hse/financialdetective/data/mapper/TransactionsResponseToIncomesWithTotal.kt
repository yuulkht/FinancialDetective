package ru.hse.financialdetective.data.mapper

import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.domain.model.IncomesWithTotal

fun TransactionsResponse.toIncomesDomain(): IncomesWithTotal {
    val incomes = transactions
        .filter { it.categoryDto.isIncome }
        .map { transaction ->
            Income(
                id = transaction.id,
                category = transaction.categoryDto.name,
                comment = transaction.comment ?: "",
                date = transaction.transactionDate,
                amount = transaction.amount.toDouble(),
                currency = transaction.account.currency
            )
        }
        .sortedByDescending { it.date }

    return IncomesWithTotal(incomes)
}