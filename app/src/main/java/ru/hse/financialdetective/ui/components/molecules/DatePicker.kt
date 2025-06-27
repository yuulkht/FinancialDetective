package ru.hse.financialdetective.ui.components.molecules

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreenLight
import ru.hse.financialdetective.ui.theme.TextColor
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate?) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
            ?.atStartOfDay(ZoneOffset.UTC)
            ?.toInstant()
            ?.toEpochMilli(),
        yearRange = 2000..2100
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row(
                modifier = Modifier
                    .background(GreenLight)
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {
                    TextButton(onClick = onDismiss) {
                        Text(
                            style = MaterialTheme.typography.labelLarge,
                            text = stringResource(R.string.cancel),
                            color = TextColor,
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    TextButton(
                        onClick = {
                            onDateSelected(state.selectedDateMillis?.let {
                                Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDate()
                            })
                            onDismiss()
                        },
                        enabled = state.selectedDateMillis != null
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelLarge,
                            text = stringResource(R.string.ok),
                            color = TextColor
                        )
                    }
                }
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = GreenLight
        )
    ) {
        DatePicker(
            state = state,
            title = null,
            headline = null,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                containerColor = GreenLight,
                selectedDayContainerColor = GreenBright,
                selectedDayContentColor = MaterialTheme.colorScheme.onSurface,
                todayContentColor = MaterialTheme.colorScheme.onSurface,
                todayDateBorderColor = Color.Transparent,
            )
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CustomDatePickerPreview() {
    CustomDatePicker(
        selectedDate = LocalDate.now(),
        onDateSelected = {},
        onDismiss = {}
    )
}


