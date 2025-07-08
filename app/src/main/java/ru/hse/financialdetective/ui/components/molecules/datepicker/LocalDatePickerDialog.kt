package ru.hse.financialdetective.ui.components.molecules.datepicker

import androidx.compose.runtime.Composable
import java.time.LocalDate

@Composable
fun LocalDatePickerDialog(
    selectedDate: LocalDate?,
    onDateClick: (LocalDate?) -> Unit,
    onDismiss: () -> Unit
) {
    CustomDatePicker(
        selectedDate = selectedDate,
        onDateSelected = { date ->
            onDateClick(date)
        },
        onDismiss = onDismiss
    )
}
