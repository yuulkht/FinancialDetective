package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.ui.components.molecules.listitems.ListExpenseItem
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel
import ru.hse.financialdetective.ui.uimodel.model.ExpenseUiModel

@Composable
fun ExpensesList(
    expenses: List<ExpenseUiModel>,
    modifier: Modifier = Modifier,
    onExpenseClick: (ExpenseUiModel) -> Unit = {}
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(expenses) { expense ->
            ListExpenseItem(
                emoji = expense.emoji,
                category = expense.category,
                amount = expense.amount,
                currency = expense.currency.symbol,
                onExpenseClick = {
                    onExpenseClick(expense)
                }
            )

        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun ExpensesListPreview() {
    val mockExpenses = listOf(
        ExpenseUiModel(
            id = 1,
            emoji = "🏠",
            category = "Аренда квартиры",
            amount = "25000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        ExpenseUiModel(
            id = 2,
            emoji = "👗",
            category = "Одежда",
            comment = "",
            amount = "4500",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        ExpenseUiModel(
            id = 3,
            emoji = "🐶",
            category = "На собачку",
            amount = "3200",
            comment = "Энни",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        ExpenseUiModel(
            id = 4,
            emoji = "🛠",
            category = "Ремонт квартиры",
            amount = "18000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        ExpenseUiModel(
            id = 5,
            emoji = "🍭",
            category = "Продукты",
            amount = "7000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        ExpenseUiModel(
            id = 6,
            emoji = "🏋️",
            category = "Спортзал",
            amount = "2500",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        ExpenseUiModel(
            id = 7,
            emoji = "💊",
            category = "Медицина",
            amount = "5200",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        )
    )

    ExpensesList(expenses = mockExpenses)
}

