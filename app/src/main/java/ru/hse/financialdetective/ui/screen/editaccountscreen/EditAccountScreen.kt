package ru.hse.financialdetective.ui.screen.editaccountscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark

@Composable
fun EditAccountScreen(
    navController: NavController,
//    viewModel: AccountsViewModel = hiltViewModel()
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScreenHeader(
                title = "Мой счёт",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.close),
                        contentDescription = stringResource(R.string.cancel),
                        modifier = Modifier
                            .size(48.dp),
                        tint = GreyDark
                    )
                },
                tailIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ok),
                        contentDescription = stringResource(R.string.ok),
                        modifier = Modifier
                            .size(48.dp),
                        tint = GreyDark
                    )
                },
                color = GreenBright
            )

//            BalanceItem(balance = (uiState as AccountUiState.Success).data.balance)
//
//            CurrencyItem(currency = (uiState as AccountUiState.Success).data.currency)
        }
    }
}