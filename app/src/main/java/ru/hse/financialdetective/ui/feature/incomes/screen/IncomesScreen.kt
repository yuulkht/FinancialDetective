package ru.hse.financialdetective.ui.feature.incomes.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import ru.hse.financialdetective.ui.components.organisms.IncomesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.feature.incomes.viewmodel.IncomesViewModel
import ru.hse.financialdetective.ui.navigation.NavigationItem
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.IncomesUiState

@Composable
fun IncomesScreen(
    navController: NavController,
    viewModel: IncomesViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTodayIncomes()
    }

    when (uiState) {
        is IncomesUiState.Loading -> {
            LoadingScreen()
        }

        is IncomesUiState.Error -> {
            ErrorScreen()
        }

        is IncomesUiState.Success -> {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ScreenHeader(
                        title = stringResource(R.string.incomes_today),
                        tailIcon = {
                            Icon(
                                painter = painterResource(R.drawable.history),
                                contentDescription = "История",
                                modifier = Modifier
                                    .size(48.dp)
                                    .clickable { navController.navigate(NavigationItem.IncomesHistory.route) },
                                tint = GreyDark
                            )
                        },
                        color = GreenBright
                    )
                    TransactionsInfoItem(
                        amount = (uiState as IncomesUiState.Success).data.total,
                        currency = (uiState as IncomesUiState.Success).data.currency.symbol
                    )
                    IncomesList(
                        incomes = (uiState as IncomesUiState.Success).data.incomes,
                        onIncomeClick = {
                            navController.navigate(NavigationItem.EditTransaction.route + "/${it.id}")
                        }
                    )
                }
                AddButton(
                    onClick = {
                        navController.navigate(NavigationItem.CreateTransaction.route + "/true")
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }
        }
    }
}