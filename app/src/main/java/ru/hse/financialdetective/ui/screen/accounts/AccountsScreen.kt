package ru.hse.financialdetective.ui.screen.accounts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.error.ErrorScreen
import ru.hse.financialdetective.ui.components.loading.LoadingScreen
import ru.hse.financialdetective.ui.components.molecules.common.AddButton
import ru.hse.financialdetective.ui.components.molecules.listitems.BalanceItem
import ru.hse.financialdetective.ui.components.molecules.listitems.CurrencyItem
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.AccountUiState

@Composable
fun AccountsScreen(
    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is AccountUiState.Loading -> {
            LoadingScreen()
        }

        is AccountUiState.Error -> {
            ErrorScreen()
        }

        is AccountUiState.Success -> {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ScreenHeader(
                        //TODO сделать хеддер подстраивающимся
                        title = (uiState as AccountUiState.Success).data.name,
                        tailIcon = {
                            Icon(
                                painter = painterResource(R.drawable.edit),
                                contentDescription = stringResource(R.string.edit),
                                modifier = Modifier
                                    .size(48.dp),
                                tint = GreyDark
                            )
                        },
                        color = GreenBright
                    )

                    BalanceItem(balance = (uiState as AccountUiState.Success).data.balance)

                    CurrencyItem(currency = (uiState as AccountUiState.Success).data.currency)

                    //TODO график
                }
                AddButton(
                    onClick = { }, //TODO
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }
        }
    }
}