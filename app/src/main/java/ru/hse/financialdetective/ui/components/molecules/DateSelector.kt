package ru.hse.financialdetective.ui.components.molecules

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.theme.GreenLight
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Начало",
) {
    val context = LocalContext.current
    val datePickerDialog = remember {
        // Пока забила на кастомность)
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
            },
            selectedDate.year,
            selectedDate.monthValue - 1,
            selectedDate.dayOfMonth
        )
    }

    ListItem(
        content = label,
        modifier = modifier,
        height = 56.dp,
        tailString = selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        isClickable = true,
        onClick = { datePickerDialog.show() },
        color = GreenLight
    )
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun DateSelectorPreview() {
    Column {
        Spacer(Modifier.height(200.dp))
        DateSelector(
            selectedDate = LocalDate.of(2025, 6, 21),
            onDateSelected = {},
        )
    }
}
