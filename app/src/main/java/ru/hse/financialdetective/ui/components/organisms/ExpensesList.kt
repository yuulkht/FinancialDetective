package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.domain.model.Expense
import ru.hse.financialdetective.ui.components.molecules.ListExpenseItem

@Composable
fun ExpensesList(
    expenses: List<Expense>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(expenses) { expense ->
            ListExpenseItem(
                emoji = expense.emoji,
                category = expense.category,
                amount = expense.amount,
                currency = expense.currency,
                //TODO добавить возможность клика
            )

        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun ExpensesListPreview() {
    val mockExpenses = listOf(
        Expense(
            id = 1,
            emoji = "🏠",
            category = "Аренда квартиры",
            amount = "25000",
            currency = "₽"
        ),
        Expense(id = 2, emoji = "👗", category = "Одежда", amount = "4500", currency = "₽"),
        Expense(id = 3, emoji = "🐶", category = "На собачку", amount = "3200", currency = "₽"),
        Expense(
            id = 4,
            emoji = "🛠",
            category = "Ремонт квартиры",
            amount = "18000",
            currency = "₽"
        ),
        Expense(id = 5, emoji = "🍭", category = "Продукты", amount = "7000", currency = "₽"),
        Expense(id = 6, emoji = "🏋️", category = "Спортзал", amount = "2500", currency = "₽"),
        Expense(id = 7, emoji = "💊", category = "Медицина", amount = "5200", currency = "₽")
    )

    ExpensesList(expenses = mockExpenses)
}

