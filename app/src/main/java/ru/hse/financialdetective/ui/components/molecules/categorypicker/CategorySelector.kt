package ru.hse.financialdetective.ui.components.molecules.categorypicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.molecules.listitems.ListItem
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel

@Composable
fun CategorySelector(
    selectedCategory: CategoryUiModel?,
    onCategorySelected: (CategoryUiModel) -> Unit,
    categoriesToChoose: List<CategoryUiModel>,
    modifier: Modifier = Modifier,
    label: String = "Категория",
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CategorySelectDialog(
            onCategorySelected = {
                onCategorySelected(it)
                showDialog = false
            },
            categories = categoriesToChoose,
            onDismiss = { showDialog = false }
        )
    }

    ListItem(
        content = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        modifier = modifier,
        height = 70.dp,
        tailIcon = {
            Text(
                text = selectedCategory?.name ?: "Выберите категорию",
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                painter = painterResource(R.drawable.more_vert),
                contentDescription = stringResource(R.string.go_to),
                modifier = Modifier
                    .size(24.dp),
                tint = GreyDark
            )
        },
        isClickable = true,
        onClick = { showDialog = true },
        color = Color.Transparent,
        showDivider = true
    )
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun CategorySelectorPreview() {
    val context = LocalContext.current
    val mockCategories = listOf(
        CategoryUiModel(id = 1, emoji = "🏠", name = "Аренда квартиры", isIncome = false),
        CategoryUiModel(id = 2, emoji = "👗", name = "Одежда", isIncome = false),
        CategoryUiModel(id = 3, emoji = "🐶", name = "На собачку", isIncome = false),
        CategoryUiModel(id = 4, emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
        CategoryUiModel(id = 5, emoji = "🍭", name = "Продукты", isIncome = false),
        CategoryUiModel(id = 6, emoji = "🏋️", name = "Спортзал", isIncome = false),
        CategoryUiModel(id = 7, emoji = "💊", name = "Медицина", isIncome = false),
        CategoryUiModel(id = 1, emoji = "🏠", name = "Аренда квартиры", isIncome = false),
        CategoryUiModel(id = 2, emoji = "👗", name = "Одежда", isIncome = false),
        CategoryUiModel(id = 3, emoji = "🐶", name = "На собачку", isIncome = false),
        CategoryUiModel(id = 4, emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
        CategoryUiModel(id = 5, emoji = "🍭", name = "Продукты", isIncome = false),
        CategoryUiModel(id = 6, emoji = "🏋️", name = "Спортзал", isIncome = false),
        CategoryUiModel(id = 7, emoji = "💊", name = "Медицина", isIncome = false),
        CategoryUiModel(id = 1, emoji = "🏠", name = "Аренда квартиры", isIncome = false),
        CategoryUiModel(id = 2, emoji = "👗", name = "Одежда", isIncome = false),
        CategoryUiModel(id = 3, emoji = "🐶", name = "На собачку", isIncome = false),
        CategoryUiModel(id = 4, emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
        CategoryUiModel(id = 5, emoji = "🍭", name = "Продукты", isIncome = false),
        CategoryUiModel(id = 6, emoji = "🏋️", name = "Спортзал", isIncome = false),
        CategoryUiModel(id = 7, emoji = "💊", name = "Медицина", isIncome = false),
    )
    val selectedCategory = remember {
        mutableStateOf(
            CategoryUiModel(
                id = 7,
                emoji = "💊",
                name = "Медицина",
                isIncome = false
            )
        )
    }

    Column {
        Spacer(Modifier.height(200.dp))
        CategorySelector(
            selectedCategory = selectedCategory.value,
            onCategorySelected = { selectedCategory.value = it },
            categoriesToChoose = mockCategories,
        )
    }
}