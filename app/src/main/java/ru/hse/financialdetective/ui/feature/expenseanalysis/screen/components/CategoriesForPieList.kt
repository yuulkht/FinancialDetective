package ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.ui.uimodel.model.CategoryForPieChartUiModel
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel

@Composable
fun CategoriesForPieList(
    expensesByCategories: List<CategoryForPieChartUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(expensesByCategories) { expenseByCategory ->
            ListCategoryForPieItem(
                emoji = expenseByCategory.emoji,
                category = expenseByCategory.name,
                amount = expenseByCategory.amount,
                currency = expenseByCategory.currency.symbol,
                percent = expenseByCategory.percent,
            )
        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun CategoriesForPieListPreview() {
    val mockCategories = listOf(
        CategoryForPieChartUiModel(
            emoji = "🏠",
            name = "Аренда квартиры",
            amount = "25000",
            percent = 0.3f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(
            emoji = "👗",
            name = "Одежда",
            amount = "4500",
            percent = 0.2f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(

            emoji = "🐶",
            name = "На собачку",
            amount = "3200",
            percent = 0.4f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(

            emoji = "🛠",
            name = "Ремонт квартиры",
            amount = "18000",
            percent = 0.5f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(
            emoji = "🍭",
            name = "Продукты",
            amount = "7000",
            percent = 1f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(

            emoji = "🏋️",
            name = "Спортзал",
            amount = "2500",
            percent = 0.1f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(
            emoji = "💊",
            name = "Медицина",
            amount = "5200",
            percent = 0.33333334f,
            currency = CurrencyUiModel.EUR
        )
    )

    CategoriesForPieList(expensesByCategories = mockCategories)
}

