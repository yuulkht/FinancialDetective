package ru.hse.financialdetective.ui.components.molecules.timepicker

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreenLight
import ru.hse.financialdetective.ui.theme.TextColor
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerAlertDialog(
    selectedTime: LocalTime?,
    onTimeSelected: (LocalTime?) -> Unit,
    onDismiss: () -> Unit
) {
    val initialState = selectedTime ?: LocalTime.now()
    val state = rememberTimePickerState(
        initialHour = initialState.hour,
        initialMinute = initialState.minute,
        is24Hour = true
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.padding(16.dp),
        containerColor = GreenLight,
        title = {
            Text(
                text = stringResource(R.string.enter_time),
                style = MaterialTheme.typography.titleMedium,
                color = TextColor
            )
        },
        text = {
            Column {
                TimePicker(
                    state = state,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TimePickerDefaults.colors(
                        containerColor = GreenLight,
                        selectorColor = GreenBright,
                        clockDialColor = GreenLight,
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.onSurface,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurface,
                        timeSelectorSelectedContainerColor = GreenBright,
                        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.onSurface,
                        timeSelectorUnselectedContainerColor = GreenLight,
                    )
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = TextColor
                    )
                }
                TextButton(
                    onClick = {
                        onTimeSelected(LocalTime.of(state.hour, state.minute))
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.ok),
                        color = TextColor
                    )
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
fun CustomTimePickerAlertDialogPreview() {
    CustomTimePickerAlertDialog(
        selectedTime = LocalTime.now(),
        onTimeSelected = {},
        onDismiss = {}
    )
}