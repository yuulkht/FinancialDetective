package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.coursework.financialdetective.ui.theme.GreyLight

@Composable
fun ListItem(
    leadIcon: @Composable () -> Unit = {},
    content: String,
    tailString: String = "",
    height: Dp = 70.dp,
    tailIcon: @Composable () -> Unit = {},
    showDivider: Boolean = true,
    onClick: () -> Unit = {},
    isClickable: Boolean = false,
    color: Color = Color.Transparent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color)
    ) {
        Row(
            modifier = Modifier
                .height(height)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable(enabled = isClickable, onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadIcon()

            Text(
                text = content,
                fontSize = 18.sp,
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = tailString,
                fontSize = 18.sp,
            )

            tailIcon()

        }

        if (showDivider) {
            HorizontalDivider(thickness = 1.dp, color = GreyLight)
        }
    }
}
