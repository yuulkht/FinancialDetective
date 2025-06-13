package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeader(
    title: String,
    leadingIcon: @Composable () -> Unit = {},
    tailIcon: @Composable () -> Unit = {},
    color: Color = Color.Transparent,
    modifier: Modifier = Modifier
) {

    Column(Modifier.background(color)) {
        Spacer(Modifier.statusBarsPadding())
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 2.dp)
            ) {
                leadingIcon()
            }
            Text(
                text = title,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 2.dp)
            ) {
                tailIcon()
            }
        }
    }

}

@Preview(apiLevel = 34)
@Composable
fun ScreenHeaderPreview() {
    ScreenHeader("Мои счета")
}
