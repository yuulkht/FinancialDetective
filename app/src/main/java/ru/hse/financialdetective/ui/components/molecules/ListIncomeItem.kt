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
import ru.hse.coursework.financialdetective.ui.theme.GreyDark

@Composable
fun ListIncomeItem(
    name: String,
    amount: String,
    currency: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        content = name,
        tailString = "$amount $currency",
        tailIcon = {
            Spacer(Modifier.width(18.dp))
            Icon(
                painter = painterResource(R.drawable.more_vert),
                contentDescription = "Перейти",
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
fun ListIncomeItemPreview() {
    ListIncomeItem(
        name = "Работа",
        amount = "100 000",
        currency = "₽"
    )
}