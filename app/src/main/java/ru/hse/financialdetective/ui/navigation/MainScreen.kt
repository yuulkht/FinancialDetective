package ru.hse.financialdetective.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.hse.financialdetective.ui.theme.GreenBright

@Composable
fun MainScreen(
    accountsFactory: ViewModelProvider.Factory,
    expensesFactory: ViewModelProvider.Factory,
    incomesFactory: ViewModelProvider.Factory,
    categoriesFactory: ViewModelProvider.Factory,
    incomesHistoryFactory: ViewModelProvider.Factory,
    expensesHistoryFactory: ViewModelProvider.Factory,
    editAccountFactory: ViewModelProvider.Factory,
    editTransactionFactory: ViewModelProvider.Factory,
    createTransactionFactory: ViewModelProvider.Factory,
    transactionsAnalysisComponent: ViewModelProvider.Factory,
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
        topBar = {
            Column(Modifier.background(GreenBright)) {
                Spacer(Modifier.statusBarsPadding())
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            FinancialDetectiveNavGraph(
                navController,
                accountsFactory,
                expensesFactory,
                incomesFactory,
                categoriesFactory,
                incomesHistoryFactory,
                expensesHistoryFactory,
                editAccountFactory,
                editTransactionFactory,
                createTransactionFactory,
                transactionsAnalysisComponent
            )
        }
    }
}