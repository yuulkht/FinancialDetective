package ru.hse.financialdetective.ui.feature.configuretransaction.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.error.ErrorScreen
import ru.hse.financialdetective.ui.components.loading.LoadingScreen
import ru.hse.financialdetective.ui.components.molecules.common.DeleteButton
import ru.hse.financialdetective.ui.components.organisms.ConfigureTransactionInfoList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.feature.configuretransaction.viewmodel.EditTransactionViewModel
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.mapper.parseLocalDate
import ru.hse.financialdetective.ui.uimodel.mapper.parseLocalTime
import ru.hse.financialdetective.ui.uimodel.model.EditTransactionEvent
import ru.hse.financialdetective.ui.uimodel.model.TransactionUiState

@Composable
fun EditTransactionScreen(
    navController: NavController,
    viewModel: EditTransactionViewModel,
    transactionId: Int
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.loadInfo(transactionId)
    }

    val context = LocalContext.current

    val event by viewModel.event.collectAsState()

    LaunchedEffect(event) {
        when (event) {
            is EditTransactionEvent.SuccessSave -> {
                Toast.makeText(context, "Изменения сохранены", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }

            is EditTransactionEvent.Error -> {
                val message = (event as EditTransactionEvent.Error).message
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
            is TransactionUiState.Loading -> {
                LoadingScreen()
            }

            is TransactionUiState.Error -> {
                ErrorScreen()
            }

            is TransactionUiState.Success -> {
                EditTransactionContent(
                    uiState = uiState as TransactionUiState.Success,
                    navController = navController,
                    viewModel = viewModel,
                )
            }
        }
    }
}


@Composable
fun EditTransactionContent(
    uiState: TransactionUiState.Success,
    navController: NavController,
    viewModel: EditTransactionViewModel,
) {
    val data = uiState.data

    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenHeader(
                title = if (viewModel.isIncome.value) "Мои доходы" else "Мои расходы",
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

            ConfigureTransactionInfoList(
                accountTitle = data.account.name,
                selectedCategory = data.category,
                onCategorySelected = viewModel::onCategoryChanged,
                categoriesToChoose = data.categories,
                sum = data.amount,
                onSumChanged = viewModel::onAmountChanged,
                currency = data.account.currency,
                selectedDate = parseLocalDate(data.date),
                onDateSelected = viewModel::onDateChanged,
                selectedTime = parseLocalTime(data.time),
                onTimeSelected = viewModel::onTimeChanged,
                comment = data.comment,
                onTextChanged = viewModel::onCommentChanged,
            )

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DeleteButton(
                    onClick = { viewModel.deleteTransaction() }
                )
            }

        }
    }
}