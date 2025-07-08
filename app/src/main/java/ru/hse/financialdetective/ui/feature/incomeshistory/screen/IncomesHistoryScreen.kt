package ru.hse.financialdetective.ui.feature.incomeshistory.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import ru.hse.financialdetective.ui.components.molecules.datepicker.DateSelector
import ru.hse.financialdetective.ui.components.molecules.listitems.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.IncomesHistoryList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.feature.incomeshistory.viewmodel.IncomesHistoryViewModel
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.IncomesUiState

@Composable
fun IncomesHistoryScreen(
    navController: NavController,
    viewModel: IncomesHistoryViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

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
                        title = "Моя история",
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.back),
                                contentDescription = stringResource(R.string.back),
                                modifier = Modifier
                                    .size(48.dp)
                                    .clickable { navController.popBackStack() },
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        tailIcon = {
                            Icon(
                                painter = painterResource(R.drawable.analysis),
                                contentDescription = "История",
                                modifier = Modifier
                                    .size(48.dp),
                                tint = GreyDark
                            )
                        },
                        color = GreenBright
                    )
                    DateSelector(
                        selectedDate = viewModel.dateFrom.value,
                        onDateSelected = {
                            viewModel.dateFrom.value = it
                            viewModel.loadForPeriodIncomes()
                        }
                    )
                    DateSelector(
                        label = stringResource(R.string.end),
                        selectedDate = viewModel.dateTo.value,
                        onDateSelected = {
                            viewModel.dateTo.value = it
                            viewModel.loadForPeriodIncomes()
                        }
                    )
                    TransactionsInfoItem(
                        amount = (uiState as IncomesUiState.Success).data.total,
                        currency = (uiState as IncomesUiState.Success).data.currency.symbol,
                        text = stringResource(R.string.sum)
                    )
                    IncomesHistoryList(incomes = (uiState as IncomesUiState.Success).data.incomes)
                }
                AddButton(
                    onClick = {}, //TODO
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }
        }
    }
}