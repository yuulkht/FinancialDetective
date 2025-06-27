package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
) {
    ListItem(
        content = text,
        tailString = "$amount $currency",
        color = GreenLight,
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