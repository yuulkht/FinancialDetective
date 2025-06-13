package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ru.hse.coursework.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.domain.model.Category
import ru.hse.financialdetective.ui.components.molecules.SearchBar
import ru.hse.financialdetective.ui.components.organisms.CategoriesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader

@Composable
fun ExpenseCategoriesScreen() {
    //TODO убрать заглушечные данные для экрана
    val mockCategories = listOf(
        Category(emoji = "🏠", name = "Аренда квартиры", isIncome = false),
        Category(emoji = "👗", name = "Одежда", isIncome = false),
        Category(emoji = "🐶", name = "На собачку", isIncome = false),
        Category(emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
        Category(emoji = "🍭", name = "Продукты", isIncome = false),
        Category(emoji = "🏋️", name = "Спортзал", isIncome = false),
        Category(emoji = "💊", name = "Медицина", isIncome = false),
    )
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(title = "Мои статьи", color = GreenBright)
        SearchBar(
            text = searchText,
            onTextChange = {},
            onSearchClick = {}
        )
        CategoriesList(categories = mockCategories)
    }
}