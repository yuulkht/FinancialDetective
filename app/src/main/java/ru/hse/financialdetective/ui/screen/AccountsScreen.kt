package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.coursework.financialdetective.ui.theme.GreenBright
import ru.hse.coursework.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.domain.model.Account
import ru.hse.financialdetective.ui.components.molecules.BalanceItem
import ru.hse.financialdetective.ui.components.molecules.CurrencyItem
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader

@Composable
fun AccountsScreen() {
    //TODO Заглушка
    val account: Account = Account(
        name = "Основной",
        balance = "-670 000 ₽",
        currency = "₽"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ScreenHeader(
            title = "Мой счёт",
            tailIcon = {
                Icon(
                    painter = painterResource(R.drawable.edit),
                    contentDescription = "Изменить",
                    modifier = Modifier
                        .size(48.dp),
                    tint = GreyDark
                )
            },
            color = GreenBright
        )

        BalanceItem(balance = account.balance)

        CurrencyItem(currency = account.currency)

        //TODO график

        //TODO Floating button


    }
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun AccountsScreenPreview() {
    AccountsScreen()
}