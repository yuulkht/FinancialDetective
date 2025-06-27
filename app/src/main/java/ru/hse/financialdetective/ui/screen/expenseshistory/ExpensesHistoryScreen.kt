package ru.hse.financialdetective.ui.screen.expenseshistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import ru.hse.financialdetective.ui.components.molecules.AddButton
import ru.hse.financialdetective.ui.components.molecules.DateSelector
import ru.hse.financialdetective.ui.components.molecules.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.ExpensesHistoryList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.screen.mockOnAddCLick
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState

@Composable
fun ExpensesHistoryScreen(
    navController: NavController,
    viewModel: ExpensesHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadForPeriodExpenses()
    }

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
                        title = "Моя история",
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.back),
                                contentDescription = stringResource(R.string.back),
                                modifier = Modifier
                                    .size(48.dp)
                                    .clickable { navController.popBackStack() },
                                tint = MaterialTheme.colorScheme.onSurface //TODO
                            )
                        },
                        tailIcon = {
                            Icon(
                                painter = painterResource(R.drawable.analysis),
                                contentDescription = stringResource(R.string.history),
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
                            viewModel.loadForPeriodExpenses()
                        }
                    )
                    DateSelector(
                        label = stringResource(R.string.end),
                        selectedDate = viewModel.dateTo.value,
                        onDateSelected = {
                            viewModel.dateTo.value = it
                            viewModel.loadForPeriodExpenses()
                        }
                    )
                    TransactionsInfoItem(
                        amount = (uiState as ExpensesUiState.Success).data.total,
                        currency = (uiState as ExpensesUiState.Success).data.currency,
                        text = "Сумма"
                    )
                    ExpensesHistoryList(expenses = (uiState as ExpensesUiState.Success).data.expenses)
                }
                AddButton(
                    onClick = mockOnAddCLick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
            }
        }
    }
}