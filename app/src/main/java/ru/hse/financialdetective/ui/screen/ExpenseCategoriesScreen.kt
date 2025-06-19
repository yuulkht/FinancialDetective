package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import ru.hse.financialdetective.ui.components.molecules.SearchBar
import ru.hse.financialdetective.ui.components.organisms.CategoriesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel

// Моковые данные
val mockCategories = listOf(
    CategoryUiModel(id = 1, emoji = "🏠", name = "Аренда квартиры", isIncome = false),
    CategoryUiModel(id = 2, emoji = "👗", name = "Одежда", isIncome = false),
    CategoryUiModel(id = 3, emoji = "🐶", name = "На собачку", isIncome = false),
    CategoryUiModel(id = 4, emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
    CategoryUiModel(id = 5, emoji = "🍭", name = "Продукты", isIncome = false),
    CategoryUiModel(id = 6, emoji = "🏋️", name = "Спортзал", isIncome = false),
    CategoryUiModel(id = 7, emoji = "💊", name = "Медицина", isIncome = false),
)
var mockSearchText = mutableStateOf("")

@Composable
fun ExpenseCategoriesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(title = "Мои статьи", color = GreenBright)
        SearchBar(
            text = mockSearchText.value,
            onTextChange = {},
            onSearchClick = {}
        )
        CategoriesList(categories = mockCategories)
    }
}