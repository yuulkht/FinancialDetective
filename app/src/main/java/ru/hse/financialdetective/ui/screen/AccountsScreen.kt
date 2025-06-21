package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.domain.model.Account
import ru.hse.financialdetective.ui.components.molecules.AddButton
import ru.hse.financialdetective.ui.components.molecules.BalanceItem
import ru.hse.financialdetective.ui.components.molecules.CurrencyItem
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.AccountUiModel

// Моковые данные
val mockAccount: AccountUiModel = Account(
    id = 1,
    name = "Основной",
    balance = "-670 000 ₽",
    currency = "₽"
).toUi()

val mockOnAddCLick = {}

@Composable
fun AccountsScreen(
    navController: NavController,
) {
    Box {
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

            BalanceItem(balance = mockAccount.balance)

            CurrencyItem(currency = mockAccount.currency)

            //TODO график
        }
        AddButton(
            onClick = mockOnAddCLick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}