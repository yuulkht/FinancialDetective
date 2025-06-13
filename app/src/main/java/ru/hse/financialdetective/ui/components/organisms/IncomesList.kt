package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.ui.components.molecules.ListIncomeItem

@Composable
fun IncomesList(
    incomes: List<Income>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(incomes) { income ->
            ListIncomeItem(
                name = income.name,
                amount = income.amount,
                currency = income.currency,
                //TODO добавить возможность клика
            )

        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun IncomesListPreview() {
    val mockIncomes = listOf(
        Income(id = 2, name = "Работа", amount = "100 000", currency = "₽"),
        Income(id = 3, name = "Подработка", amount = "50 000", currency = "₽"),
    )

    IncomesList(incomes = mockIncomes)
}

