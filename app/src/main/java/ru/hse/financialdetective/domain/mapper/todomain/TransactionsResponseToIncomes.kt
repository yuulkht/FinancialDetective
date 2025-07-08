package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Currency
import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.domain.model.Incomes

fun TransactionsResponse.toIncomesDomain(currency: Currency): Incomes {
    val incomes = transactions
        .filter { it.categoryDto.isIncome }
        .map { transaction ->
            Income(
                id = transaction.id,
                category = transaction.categoryDto.name,
                comment = transaction.comment ?: "",
                date = transaction.transactionDate,
                amount = transaction.amount.toDouble(),
                currency = transaction.account.currency.toCurrencyDomain()
            )
        }
        .sortedByDescending { it.date }

    return Incomes(incomes, currency)
}