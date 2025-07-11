package ru.hse.financialdetective.ui.components.molecules.categorypicker

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.hse.financialdetective.ui.components.organisms.CategoriesList
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel

@Composable
fun CategorySelectDialog(
    onCategorySelected: (CategoryUiModel) -> Unit,
    categories: List<CategoryUiModel>,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 100.dp, horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Column {
                    Text(
                        text = "Выберите категорию",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    CategoriesList(
                        categories = categories,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onCategoryClick = onCategorySelected
                    )

                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("Закрыть")
                    }
                }
            }
        }
    )
}

@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CategoryPickerDialogPreview() {
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
    CategorySelectDialog(
        onCategorySelected = {
            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
        },
        categories = mockCategories,
        onDismiss = {}
    )
}