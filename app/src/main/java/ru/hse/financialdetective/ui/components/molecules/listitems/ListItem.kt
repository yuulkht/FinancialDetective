package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.theme.GreyLight

@Composable
fun ListItem(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    comment: String = "",
    leadIcon: @Composable () -> Unit = {},
    tailString: String = "",
    tailDate: String = "",
    height: Dp = 70.dp,
    tailIcon: @Composable () -> Unit = {},
    showDivider: Boolean = true,
    onClick: () -> Unit = {},
    isClickable: Boolean = false,
    color: Color = Color.Transparent,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color)
            .clickable(enabled = isClickable, onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .height(height)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadIcon()
            Column {
                content()
                if (comment != "") {
                    Text(
                        text = comment,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GreyDark
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Column {
                Text(
                    text = tailString,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.End)
                )
                if (tailDate != "") {
                    Text(
                        text = tailDate,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GreyDark,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }


            tailIcon()

        }

        if (showDivider) {
            HorizontalDivider(thickness = 1.dp, color = GreyLight)
        }
    }
}
