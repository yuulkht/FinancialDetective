package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.ui.components.molecules.listitems.ListIncomeItem
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel
import ru.hse.financialdetective.ui.uimodel.model.IncomeUiModel

@Composable
fun IncomesList(
    incomes: List<IncomeUiModel>,
    modifier: Modifier = Modifier,
    onIncomeClick: (IncomeUiModel) -> Unit = {}
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(incomes) { income ->
            ListIncomeItem(
                name = income.category,
                amount = income.amount,
                currency = income.currency.symbol,
                onIncomeClick = {
                    onIncomeClick(income)
                }
            )

        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun IncomesListPreview() {
    val mockIncomes = listOf(
        IncomeUiModel(
            id = 2,
            category = "Работа",
            amount = "100 000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
        IncomeUiModel(
            id = 3,
            category = "Подработка",
            amount = "50 000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = CurrencyUiModel.EUR
        ),
    )

    IncomesList(incomes = mockIncomes)
}

