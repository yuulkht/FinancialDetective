package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Currency
import ru.hse.financialdetective.domain.model.TransactionAnalysis
import ru.hse.financialdetective.domain.model.TransactionsAnalysis

fun TransactionsResponse.toTransactionsAnalysisDomain(
    currency: Currency,
    isIncome: Boolean
): TransactionsAnalysis {
    val transactions = transactions
        .filter { it.categoryDto.isIncome == isIncome }
        .map { transaction ->
            TransactionAnalysis(
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

    return TransactionsAnalysis(transactions, currency)
}