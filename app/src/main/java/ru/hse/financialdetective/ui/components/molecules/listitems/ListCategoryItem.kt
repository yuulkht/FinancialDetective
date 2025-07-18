package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.theme.GreenLight

@Composable
fun ListCategoryItem(
    emoji: String,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isClickable: Boolean = false
) {
    ListItem(
        leadIcon = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(GreenLight, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
        },
        content = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        showDivider = true,
        onClick = onClick,
        isClickable = isClickable,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListCategoryItemPreview() {
    ListCategoryItem(
        emoji = "💰",
        title = "Зарплата"
    )
}
