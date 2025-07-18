package ru.hse.financialdetective.ui.components.organisms

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.molecules.categorypicker.CategorySelector
import ru.hse.financialdetective.ui.components.molecules.datepicker.DateSelector
import ru.hse.financialdetective.ui.components.molecules.listitems.EditComment
import ru.hse.financialdetective.ui.components.molecules.listitems.EditTransactionAccount
import ru.hse.financialdetective.ui.components.molecules.listitems.EditTransactionSum
import ru.hse.financialdetective.ui.components.molecules.timepicker.TimeSelector
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ConfigureTransactionInfoList(
    accountTitle: String,
    selectedCategory: CategoryUiModel?,
    onCategorySelected: (CategoryUiModel) -> Unit,
    categoriesToChoose: List<CategoryUiModel>,
    sum: String,
    onSumChanged: (String) -> Unit,
    currency: CurrencyUiModel,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate?) -> Unit,
    selectedTime: LocalTime,
    onTimeSelected: (LocalTime?) -> Unit,
    comment: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        EditTransactionAccount(
            account = accountTitle
        )
        CategorySelector(
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected,
            categoriesToChoose = categoriesToChoose
        )
        EditTransactionSum(
            sum = sum,
            onSumChanged = onSumChanged,
            currency = currency
        )
        DateSelector(
            selectedDate = selectedDate,
            onDateSelected = onDateSelected,
            label = stringResource(R.string.date),
            height = 70.dp,
            color = Color.Transparent
        )
        TimeSelector(
            selectedTime = selectedTime,
            onTimeSelected = onTimeSelected
        )
        EditComment(
            comment = comment,
            onTextChanged = onTextChanged
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ConfigureTransactionInfoListPreview() {
    val mockCategories = listOf(
        CategoryUiModel(id = 1, emoji = "🏠", name = "Аренда квартиры", isIncome = false),
        CategoryUiModel(id = 2, emoji = "👗", name = "Одежда", isIncome = false),
        CategoryUiModel(id = 3, emoji = "🐶", name = "На собачку", isIncome = false),
        CategoryUiModel(id = 4, emoji = "🛠", name = "Ремонт квартиры", isIncome = false),
        CategoryUiModel(id = 5, emoji = "🍭", name = "Продукты", isIncome = false),
        CategoryUiModel(id = 6, emoji = "🏋️", name = "Спортзал", isIncome = false),
        CategoryUiModel(id = 7, emoji = "💊", name = "Медицина", isIncome = false)
    )

    val selectedCategory = remember { mutableStateOf<CategoryUiModel?>(null) }
    val sum = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedTime = remember { mutableStateOf(LocalTime.now()) }

    ConfigureTransactionInfoList(
        accountTitle = "Сбербанк",
        selectedCategory = selectedCategory.value,
        onCategorySelected = { selectedCategory.value = it },
        categoriesToChoose = mockCategories,
        sum = sum.value,
        onSumChanged = { sum.value = it },
        currency = CurrencyUiModel.RUB,
        selectedDate = selectedDate.value,
        onDateSelected = { date -> date?.let { selectedDate.value = it } },
        selectedTime = selectedTime.value,
        onTimeSelected = { time -> time?.let { selectedTime.value = it } },
        comment = comment.value,
        onTextChanged = { comment.value = it }
    )
}