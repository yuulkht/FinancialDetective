package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreyDark

@Composable
fun ListIncomeHistoryItem(
    name: String,
    amount: String,
    currency: String,
    date: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        content = name,
        tailString = "$amount $currency",
        tailIcon = {
            Spacer(Modifier.width(16.dp))
            Icon(
                painter = painterResource(R.drawable.more_vert),
                contentDescription = "Перейти",
                modifier = Modifier
                    .size(24.dp),
                tint = GreyDark
            )
        },
        tailDate = date,
        showDivider = true,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListIncomeHistoryItemPreview() {
    ListIncomeHistoryItem(
        name = "Работа",
        amount = "100 000",
        currency = "₽",
        date = "22:01"
    )
}