package ru.hse.financialdetective.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.components.organisms.SettingsList
import ru.hse.financialdetective.ui.theme.GreenBright

var checked = mutableStateOf(false)

@Composable
fun SettingsScreen(
    navController: NavController,
) {
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
