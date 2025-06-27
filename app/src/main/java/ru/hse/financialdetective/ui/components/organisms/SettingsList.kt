package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.molecules.ListArrowItem
import ru.hse.financialdetective.ui.components.molecules.ListToggleItem

@Composable
fun SettingsList(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onBaseColorClick: () -> Unit,
    onSoundsClick: () -> Unit,
    onHapticsClick: () -> Unit,
    onCodePasswordClick: () -> Unit,
    onSynchronizationClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onAboutProgramClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            ListToggleItem(
                title = stringResource(R.string.dark_theme),
                checked = darkTheme,
                onCheckedChange = onDarkThemeChange
            )
        }
        item {
            ListArrowItem(
                title = stringResource(R.string.primary_color),
                onClick = onBaseColorClick
            )
        }
        item {
            ListArrowItem(title = stringResource(R.string.sounds), onClick = onSoundsClick)
        }
        item {
            ListArrowItem(title = stringResource(R.string.haptics), onClick = onHapticsClick)
        }
        item {
            ListArrowItem(
                title = stringResource(R.string.password_code),
                onClick = onCodePasswordClick
            )
        }
        item {
            ListArrowItem(title = stringResource(R.string.sync), onClick = onSynchronizationClick)
        }
        item {
            ListArrowItem(title = stringResource(R.string.language), onClick = onLanguageClick)
        }
        item {
            ListArrowItem(title = stringResource(R.string.about), onClick = onAboutProgramClick)
        }
    }
}

@Composable
@Preview(apiLevel = 35, showBackground = true)
fun SettingsListPreview() {
    var checked by remember { mutableStateOf(false) }

    SettingsList(
        darkTheme = checked,
        onDarkThemeChange = { newValue -> checked = newValue },
        onBaseColorClick = {},
        onSoundsClick = {},
        onHapticsClick = {},
        onCodePasswordClick = {},
        onSynchronizationClick = {},
        onLanguageClick = {},
        onAboutProgramClick = {}
    )
}