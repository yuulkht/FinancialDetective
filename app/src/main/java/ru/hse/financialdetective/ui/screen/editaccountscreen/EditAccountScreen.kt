package ru.hse.financialdetective.ui.screen.editaccountscreen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.error.ErrorScreen
import ru.hse.financialdetective.ui.components.loading.LoadingScreen
import ru.hse.financialdetective.ui.components.molecules.listitems.CurrencyItem
import ru.hse.financialdetective.ui.components.molecules.listitems.EditAccountName
import ru.hse.financialdetective.ui.components.molecules.listitems.EditBalance
import ru.hse.financialdetective.ui.components.organisms.CurrencyChangeBottomSheet
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.AccountUiState
import ru.hse.financialdetective.ui.uimodel.model.EditAccountEvent

@Composable
fun EditAccountScreen(
    navController: NavController,
    viewModel: EditAccountViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val showCurrencyChoiceSheet = viewModel.showCurrencyChoiceSheet

    val context = LocalContext.current

    val event by viewModel.event.collectAsState()

    LaunchedEffect(event) {
        when (event) {
            is EditAccountEvent.SuccessSave -> {
                Toast.makeText(context, "Изменения сохранены", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }

            is EditAccountEvent.Error -> {
                val message = (event as EditAccountEvent.Error).message
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_LONG
                ).show()
            }

            null -> {}
        }

        viewModel.resetEvent()

    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is AccountUiState.Loading -> {
                LoadingScreen()
            }

            is AccountUiState.Error -> {
                ErrorScreen()
            }

            is AccountUiState.Success -> {
                EditAccountContent(
                    uiState = uiState as AccountUiState.Success,
                    navController = navController,
                    viewModel = viewModel,
                    showCurrencyChoiceSheet = showCurrencyChoiceSheet
                )
            }
        }
    }
}


@Composable
fun EditAccountContent(
    uiState: AccountUiState.Success,
    navController: NavController,
    viewModel: EditAccountViewModel,
    showCurrencyChoiceSheet: MutableState<Boolean>
) {
    val data = uiState.data

    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenHeader(
                title = "Мой счёт",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.close),
                        contentDescription = stringResource(R.string.cancel),
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { navController.popBackStack() },
                        tint = GreyDark
                    )
                },
                tailIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ok),
                        contentDescription = stringResource(R.string.ok),
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { viewModel.save() },
                        tint = GreyDark
                    )
                },
                color = GreenBright
            )

            EditAccountName(
                accountTitle = data.name,
                onTextChanged = viewModel::onTitleChanged
            )

            EditBalance(
                balance = data.balance,
                onTextChanged = viewModel::onBalanceChanged
            )

            CurrencyItem(
                currency = data.currency.symbol,
                isClickable = true,
                onClick = { showCurrencyChoiceSheet.value = true }
            )
        }

        if (showCurrencyChoiceSheet.value) {
            CurrencyChangeBottomSheet(
                currentCurrency = data.currency,
                onCurrencySelected = viewModel::onCurrencyChanged,
                onDismissRequest = { showCurrencyChoiceSheet.value = false }
            )
        }
    }
}
