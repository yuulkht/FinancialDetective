package ru.hse.financialdetective.domain.usecase

import ru.hse.financialdetective.domain.model.CategoriesTransactions
import ru.hse.financialdetective.domain.model.CategoryTransaction
import ru.hse.financialdetective.domain.model.TransactionsAnalysis

class CalculateCategoryTransactionsByTransactions {

    operator fun invoke(
        transactions: TransactionsAnalysis
    ): CategoriesTransactions {
        val totalAmount = transactions.items.sumOf { it.amount }

        if (totalAmount == 0.0) return CategoriesTransactions(
            emptyList(),
            totalAmount,
            transactions.currency
        )

        val grouped = transactions.items.groupBy { Triple(it.emoji, it.category, it.currency) }

        val categoryExpenses = grouped.map { (key, group) ->
            val sum = group.sumOf { it.amount }
            CategoryTransaction(
                emoji = key.first,
                name = key.second,
                amount = sum,
                percent = (sum / totalAmount).toFloat(),
                currency = key.third
            )
        }

        return CategoriesTransactions(categoryExpenses, totalAmount, transactions.currency)
    }
}
