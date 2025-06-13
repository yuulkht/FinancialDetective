package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.coursework.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.components.organisms.SettingsList

@Composable
fun SettingsScreen() {
    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(title = "Настройки", color = GreenBright)
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
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
