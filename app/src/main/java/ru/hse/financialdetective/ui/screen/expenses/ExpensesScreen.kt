package ru.hse.financialdetective.ui.screen.expenses

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.molecules.AddButton
import ru.hse.financialdetective.ui.components.molecules.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.ExpensesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.screen.mockOnAddCLick
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.ExpensesUiState

@Composable
fun ExpensesScreen(
    viewModel: ExpensesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTodayExpenses()
    }

    when (uiState) {
        is ExpensesUiState.Loading -> {}//TODO
        is ExpensesUiState.Error -> {}//TODO
        is ExpensesUiState.Success -> {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ScreenHeader(
                        title = "Расходы сегодня",
                        tailIcon = {
                            Icon(
                                painter = painterResource(R.drawable.history),
                                contentDescription = "История",
                                modifier = Modifier
                                    .size(48.dp),
                                tint = GreyDark
                            )
                        },
                        color = GreenBright
                    )
                    TransactionsInfoItem(
                        amount = (uiState as ExpensesUiState.Success).data.total,
                        currency = (uiState as ExpensesUiState.Success).data.currency
                    )
                    ExpensesList(expenses = (uiState as ExpensesUiState.Success).data.expenses)
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