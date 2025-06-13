package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.domain.model.Category
import ru.hse.financialdetective.ui.components.molecules.ListCategoryItem

@Composable
fun CategoriesList(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(categories) { category ->
            ListCategoryItem(
                emoji = category.emoji,
                title = category.name,
                //TODO добавить возможность клика на категорию
            )

        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun CategoriesListPreview() {
    val mockCategories = listOf(
        Category(emoji = "🏠", name = "Аренда квартиры", isIncome = false),
        Category(emoji = "👗", name = "Одежда", isIncome = false),
        Category(emoji = "🐶", name = "На собачку", isIncome = false),
        Category(emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
        Category(emoji = "🍭", name = "Продукты", isIncome = false),
        Category(emoji = "🏋️", name = "Спортзал", isIncome = false),
        Category(emoji = "💊", name = "Медицина", isIncome = false),
    )

    CategoriesList(categories = mockCategories)
}
