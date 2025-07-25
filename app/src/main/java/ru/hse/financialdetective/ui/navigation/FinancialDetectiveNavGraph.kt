package ru.hse.financialdetective.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.hse.financialdetective.ui.feature.SettingsScreen
import ru.hse.financialdetective.ui.feature.accounts.screen.AccountsScreen
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel
import ru.hse.financialdetective.ui.feature.categories.screen.ExpenseCategoriesScreen
import ru.hse.financialdetective.ui.feature.categories.viewmodel.ExpenseCategoriesViewModel
import ru.hse.financialdetective.ui.feature.createtransaction.screen.CreateTransactionScreen
import ru.hse.financialdetective.ui.feature.createtransaction.viewmodel.CreateTransactionViewModel
import ru.hse.financialdetective.ui.feature.editaccountscreen.screen.EditAccountScreen
import ru.hse.financialdetective.ui.feature.editaccountscreen.viewmodel.EditAccountViewModel
import ru.hse.financialdetective.ui.feature.edittransaction.screen.EditTransactionScreen
import ru.hse.financialdetective.ui.feature.edittransaction.viewmodel.EditTransactionViewModel
import ru.hse.financialdetective.ui.feature.expenseanalysis.screen.TransactionsAnalysisScreen
import ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel.TransactionsAnalysisViewModel
import ru.hse.financialdetective.ui.feature.expenses.screen.ExpensesScreen
import ru.hse.financialdetective.ui.feature.expenses.viewmodel.ExpensesViewModel
import ru.hse.financialdetective.ui.feature.expenseshistory.screen.ExpensesHistoryScreen
import ru.hse.financialdetective.ui.feature.expenseshistory.viewmodel.ExpensesHistoryViewModel
import ru.hse.financialdetective.ui.feature.incomes.screen.IncomesScreen
import ru.hse.financialdetective.ui.feature.incomes.viewmodel.IncomesViewModel
import ru.hse.financialdetective.ui.feature.incomeshistory.screen.IncomesHistoryScreen
import ru.hse.financialdetective.ui.feature.incomeshistory.viewmodel.IncomesHistoryViewModel
import ru.hse.financialdetective.ui.uimodel.mapper.instantStringToLocalDate

@Composable
fun FinancialDetectiveNavGraph(
    navController: NavHostController,
    accountsFactory: ViewModelProvider.Factory,
    expensesFactory: ViewModelProvider.Factory,
    incomesFactory: ViewModelProvider.Factory,
    categoriesFactory: ViewModelProvider.Factory,
    incomesHistoryFactory: ViewModelProvider.Factory,
    expensesHistoryFactory: ViewModelProvider.Factory,
    editAccountFactory: ViewModelProvider.Factory,
    editTransactionFactory: ViewModelProvider.Factory,
    createTransactionFactory: ViewModelProvider.Factory,
    transactionsAnalysisFactory: ViewModelProvider.Factory,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Expenses.route
    ) {
        composable(NavigationItem.Expenses.route) { backStackEntry ->
            val viewModel: ExpensesViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = expensesFactory
            )
            ExpensesScreen(navController, viewModel)
        }
        composable(NavigationItem.Incomes.route) { backStackEntry ->
            val viewModel: IncomesViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = incomesFactory
            )
            IncomesScreen(navController, viewModel)
        }
        composable(NavigationItem.Accounts.route) { backStackEntry ->
            val viewModel: AccountsViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = accountsFactory
            )
            AccountsScreen(navController, viewModel)
        }
        composable(NavigationItem.ExpenseCategories.route) { backStackEntry ->
            val viewModel: ExpenseCategoriesViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = categoriesFactory
            )
            ExpenseCategoriesScreen(navController, viewModel)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(navController)
        }
        composable(NavigationItem.IncomesHistory.route) { backStackEntry ->
            val viewModel: IncomesHistoryViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = incomesHistoryFactory
            )
            IncomesHistoryScreen(navController, viewModel)
        }
        composable(NavigationItem.ExpensesHistory.route) { backStackEntry ->
            val viewModel: ExpensesHistoryViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = expensesHistoryFactory
            )
            ExpensesHistoryScreen(navController, viewModel)
        }
        composable(NavigationItem.EditAccount.route) { backStackEntry ->
            val viewModel: EditAccountViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = editAccountFactory
            )
            EditAccountScreen(navController, viewModel)
        }
        composable(NavigationItem.EditTransaction.route + "/{transactionId}") { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getString("transactionId")
            when (val id = transactionId?.toIntOrNull()) {
                null -> {}
                else -> {
                    val viewModel: EditTransactionViewModel = viewModel(
                        viewModelStoreOwner = backStackEntry,
                        factory = editTransactionFactory
                    )
                    EditTransactionScreen(navController, viewModel, id)
                }
            }
        }
        composable(NavigationItem.CreateTransaction.route + "/{isIncome}") { backStackEntry ->
            val isIncome = backStackEntry.arguments?.getString("isIncome")
            when (val income = isIncome?.toBoolean()) {
                null -> {}
                else -> {
                    val viewModel: CreateTransactionViewModel = viewModel(
                        viewModelStoreOwner = backStackEntry,
                        factory = createTransactionFactory
                    )
                    CreateTransactionScreen(navController, viewModel, income)
                }
            }
        }
        composable(NavigationItem.TransactionsAnalysis.route + "/{dateFrom}/{dateTo}/{isIncome}") { backStackEntry ->
            val dateFromStr = backStackEntry.arguments?.getString("dateFrom")
            val dateToStr = backStackEntry.arguments?.getString("dateTo")
            val isIncome = backStackEntry.arguments?.getString("isIncome")

            when (val income = isIncome?.toBoolean()) {
                null -> {}
                else -> {
                    if (dateFromStr != null && dateToStr != null) {
                        val dateFrom = instantStringToLocalDate(dateFromStr)
                        val dateTo = instantStringToLocalDate(dateToStr)

                        val viewModel: TransactionsAnalysisViewModel = viewModel(
                            viewModelStoreOwner = backStackEntry,
                            factory = transactionsAnalysisFactory
                        )
                        TransactionsAnalysisScreen(
                            navController = navController,
                            viewModel = viewModel,
                            dateFrom = dateFrom,
                            dateTo = dateTo,
                            isIncome = income
                        )
                    }
                }
            }
        }

    }
}