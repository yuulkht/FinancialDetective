package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreenLight
import ru.hse.financialdetective.ui.theme.GreyDark

@Composable
fun ListExpenseItem(
    emoji: String,
    category: String,
    amount: String,
    currency: String,
    modifier: Modifier = Modifier,
    comment: String = ""
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
                text = category,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        comment = comment,
        tailString = "$amount $currency",
        tailIcon = {
            Spacer(Modifier.width(16.dp))
            Icon(
                painter = painterResource(R.drawable.more_vert),
                contentDescription = stringResource(R.string.go_to),
                modifier = Modifier
                    .size(24.dp),
                tint = GreyDark
            )
        },
        showDivider = true,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListExpenseItemPreview() {
    ListExpenseItem(
        emoji = "🏠",
        category = "Аренда квартиры",
        amount = "100 000",
        currency = "₽",
        comment = "Сокольники",
    )
}