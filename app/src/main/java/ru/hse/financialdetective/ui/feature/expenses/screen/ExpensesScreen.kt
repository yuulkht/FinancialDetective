package ru.hse.financialdetective.ui.feature.expenses.screen

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.error.ErrorScreen
import ru.hse.financialdetective.ui.components.loading.LoadingScreen
import ru.hse.financialdetective.ui.components.molecules.common.AddButton
import ru.hse.financialdetective.ui.components.molecules.listitems.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.ExpensesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.feature.expenses.viewmodel.ExpensesViewModel
import ru.hse.financialdetective.ui.navigation.NavigationItem
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState

@Composable
fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpensesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ExpensesUiState.Loading -> {
            LoadingScreen()
        }

        is ExpensesUiState.Error -> {
            ErrorScreen()
        }

        is ExpensesUiState.Success -> {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ScreenHeader(
                        title = stringResource(R.string.expenses_today),
                        tailIcon = {
                            Icon(
                                painter = painterResource(R.drawable.history),
                                contentDescription = stringResource(R.string.history),
                                modifier = Modifier
                                    .size(48.dp)
                                    .clickable { navController.navigate(NavigationItem.ExpensesHistory.route) },
                                tint = GreyDark,

                                )
                        },
                        color = GreenBright
                    )
                    TransactionsInfoItem(
                        amount = (uiState as ExpensesUiState.Success).data.total,
                        currency = (uiState as ExpensesUiState.Success).data.currency.symbol
                    )
                    ExpensesList(expenses = (uiState as ExpensesUiState.Success).data.expenses)
                }
                AddButton(
                    onClick = { }, //todo
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }
        }
    }
}