package ru.hse.financialdetective.ui.components.molecules.datepicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.molecules.listitems.ListItem
import ru.hse.financialdetective.ui.theme.GreenLight
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.start),
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        LocalDatePickerDialog(
            selectedDate = selectedDate,
            onDateClick = {
                onDateSelected(it)
                showDialog = false
            },
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
        height = 56.dp,
        tailString = selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        isClickable = true,
        onClick = { showDialog = true },
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
