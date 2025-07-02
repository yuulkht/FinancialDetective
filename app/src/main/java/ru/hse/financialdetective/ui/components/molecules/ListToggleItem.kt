package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreenLight

@Composable
fun ListToggleItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        content = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        tailIcon = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors().copy(
                    checkedTrackColor = GreenLight,
                    checkedThumbColor = GreenBright
                ),
                thumbContent = if (checked) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }
            )
        },
        height = 56.dp,
        modifier = modifier
    )
}


@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListToggleItemPreview() {
    var checked by remember { mutableStateOf(false) }

    ListToggleItem(
        title = "Тёмная тема",
        checked = checked,
        onCheckedChange = { newValue -> checked = newValue }
    )
}
