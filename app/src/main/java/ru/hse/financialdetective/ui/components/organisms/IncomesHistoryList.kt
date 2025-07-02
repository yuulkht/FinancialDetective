package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.ui.components.molecules.listitems.ListIncomeHistoryItem
import ru.hse.financialdetective.ui.uimodel.model.IncomeUiModel

@Composable
fun IncomesHistoryList(
    incomes: List<IncomeUiModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(incomes) { income ->
            ListIncomeHistoryItem(
                name = income.category,
                amount = income.amount,
                currency = income.currency,
                date = income.date
                //TODO добавить возможность клика
            )

        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun IncomesHistoryListPreview() {
    val mockIncomes = listOf(
        IncomeUiModel(
            id = 2,
            category = "Работа",
            amount = "100 000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        IncomeUiModel(
            id = 3,
            category = "Подработка",
            amount = "50 000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
    )

    IncomesHistoryList(incomes = mockIncomes)
}

