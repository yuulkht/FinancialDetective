package ru.hse.financialdetective.domain.usecase

import ru.hse.financialdetective.domain.model.CategoriesExpenses
import ru.hse.financialdetective.domain.model.CategoryExpense
import ru.hse.financialdetective.domain.model.Expenses

class CalculateCategoryExpensesByExpenses {

    operator fun invoke(
        expenses: Expenses
    ): CategoriesExpenses {
        val totalAmount = expenses.items.sumOf { it.amount }

        if (totalAmount == 0.0) return CategoriesExpenses(
            emptyList(),
            totalAmount,
            expenses.currency
        )

        val grouped = expenses.items.groupBy { Triple(it.emoji, it.category, it.currency) }

        val categoryExpenses = grouped.map { (key, group) ->
            val sum = group.sumOf { it.amount }
            CategoryExpense(
                emoji = key.first,
                name = key.second,
                amount = sum,
                percent = (sum / totalAmount).toFloat(),
                currency = key.third
            )
        }

        return CategoriesExpenses(categoryExpenses, totalAmount, expenses.currency)
    }
}
