package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.coursework.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.components.organisms.SettingsList

var checked = mutableStateOf(false)

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(title = "Настройки", color = GreenBright)
        SettingsList(
            darkTheme = checked.value,
            onDarkThemeChange = { newValue -> checked.value = newValue },
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
