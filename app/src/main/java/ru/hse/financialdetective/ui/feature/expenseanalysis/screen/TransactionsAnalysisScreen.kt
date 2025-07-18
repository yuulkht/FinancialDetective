package ru.hse.financialdetective.ui.feature.expenseanalysis.screen

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.error.ErrorScreen
import ru.hse.financialdetective.ui.components.loading.LoadingScreen
import ru.hse.financialdetective.ui.components.molecules.listitems.ListItem
import ru.hse.financialdetective.ui.components.molecules.listitems.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components.CategoriesForPieList
import ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components.DatePeriodComponent
import ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components.PieChartWithLegend
import ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel.TransactionsAnalysisViewModel
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.theme.GreyLight
import ru.hse.financialdetective.ui.uimodel.model.CategoriesForPieUiState
import java.time.LocalDate


@Composable
fun TransactionsAnalysisScreen(
    navController: NavController,
    viewModel: TransactionsAnalysisViewModel,
    dateFrom: LocalDate,
    dateTo: LocalDate,
    isIncome: Boolean
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.loadForPeriodExpenses(dateFrom, dateTo, isIncome)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is CategoriesForPieUiState.Loading -> {
                LoadingScreen()
            }

            is CategoriesForPieUiState.Error -> {
                ErrorScreen()
            }

            is CategoriesForPieUiState.Success -> {
                Box {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ScreenHeader(
                            title = stringResource(R.string.analysis),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.back),
                                    contentDescription = stringResource(R.string.back),
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable { navController.popBackStack() },
                                    tint = GreyDark
                                )
                            }
                        )

                        ListItem(
                            content = {
                                Text(
                                    text = stringResource(R.string.period_begin),
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            },
                            height = 70.dp,
                            tailIcon = { DatePeriodComponent(dateFrom) },
                        )

                        ListItem(
                            content = {
                                Text(
                                    text = stringResource(R.string.period_end),
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            },
                            height = 70.dp,
                            tailIcon = { DatePeriodComponent(dateTo) },
                        )

                        TransactionsInfoItem(
                            amount = (uiState as CategoriesForPieUiState.Success).data.total,
                            currency = (uiState as CategoriesForPieUiState.Success).data.currency.symbol,
                            text = "Сумма",
                            color = Color.Transparent
                        )

                        Spacer(Modifier.height(50.dp))

                        if ((uiState as CategoriesForPieUiState.Success).data.categories.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                PieChartWithLegend(
                                    data = (uiState as CategoriesForPieUiState.Success).data.categories,
                                    modifier = Modifier.size(150.dp),
                                    strokeWidth = 25f
                                )
                            }

                            Spacer(Modifier.height(30.dp))
                            HorizontalDivider(thickness = 1.dp, color = GreyLight)

                            CategoriesForPieList(
                                expensesByCategories = (uiState as CategoriesForPieUiState.Success).data.categories
                            )
                        } else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.no_expenses),
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
