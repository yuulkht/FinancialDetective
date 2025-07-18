package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreenLight

@Composable
fun TransactionsInfoItem(
    amount: String,
    currency: String,
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.total),
    color: Color = GreenLight
) {
    ListItem(
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        tailString = "$amount $currency",
        color = color,
        height = 56.dp,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun TransactionsInfoItemPreview() {
    TransactionsInfoItem(
        amount = "600 000",
        currency = "₽"
    )
}