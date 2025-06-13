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
import ru.hse.coursework.financialdetective.ui.theme.GreenLight
import ru.hse.coursework.financialdetective.ui.theme.GreyDark

@Composable
fun CurrencyItem(
    currency: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        content = "Валюта",
        tailString = currency,
        tailIcon = {
            Spacer(Modifier.width(18.dp))
            Icon(
                painter = painterResource(R.drawable.more_vert),
                contentDescription = "Выбрать валюту",
                modifier = Modifier.size(24.dp),
                tint = GreyDark
            )
        },
        color = GreenLight,
        height = 56.dp,
        showDivider = false,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun CurrencyItemPreview() {
    CurrencyItem("₽")
}