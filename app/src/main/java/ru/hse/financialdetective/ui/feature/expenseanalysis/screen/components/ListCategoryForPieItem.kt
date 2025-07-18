package ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components

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
import ru.hse.financialdetective.ui.components.molecules.listitems.ListItem
import ru.hse.financialdetective.ui.theme.GreenLight

@Composable
fun ListCategoryForPieItem(
    emoji: String,
    category: String,
    amount: String,
    percent: Float,
    currency: String,
    modifier: Modifier = Modifier,
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
        tailDate = "$amount $currency",
        content = {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        tailString = "${(percent * 100).toInt()}%",
        isClickable = true,
        showDivider = true,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListCategoryForPieItemPreview() {
    ListCategoryForPieItem(
        emoji = "🏠",
        category = "Аренда квартиры",
        amount = "100 000",
        currency = "₽",
        percent = 0.2f
    )
}