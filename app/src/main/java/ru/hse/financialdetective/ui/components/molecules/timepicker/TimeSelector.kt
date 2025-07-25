package ru.hse.financialdetective.ui.components.molecules.timepicker

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.components.molecules.listitems.ListItem
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimeSelector(
    selectedTime: LocalTime,
    onTimeSelected: (LocalTime?) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Время",
    height: Dp = 70.dp,
    color: Color = Color.Transparent
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomTimePickerAlertDialog(
            selectedTime = selectedTime,
            onTimeSelected = {
                onTimeSelected(it)
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
        height = height,
        tailString = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")),
        isClickable = true,
        onClick = { showDialog = true },
        color = color
    )
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun TimeSelectorPreview() {
    Column {
        Spacer(Modifier.height(200.dp))
        TimeSelector(
            selectedTime = LocalTime.of(14, 30),
            onTimeSelected = {},
        )
    }
}