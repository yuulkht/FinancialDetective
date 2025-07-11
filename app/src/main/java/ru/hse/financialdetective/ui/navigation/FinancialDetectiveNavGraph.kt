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
import ru.hse.financialdetective.ui.feature.editaccountscreen.screen.EditAccountScreen
import ru.hse.financialdetective.ui.feature.editaccountscreen.viewmodel.EditAccountViewModel
import ru.hse.financialdetective.ui.feature.expenses.screen.ExpensesScreen
import ru.hse.financialdetective.ui.feature.expenses.viewmodel.ExpensesViewModel
import ru.hse.financialdetective.ui.feature.expenseshistory.screen.ExpensesHistoryScreen
import ru.hse.financialdetective.ui.feature.expenseshistory.viewmodel.ExpensesHistoryViewModel
import ru.hse.financialdetective.ui.feature.incomes.screen.IncomesScreen
import ru.hse.financialdetective.ui.feature.incomes.viewmodel.IncomesViewModel
import ru.hse.financialdetective.ui.feature.incomeshistory.screen.IncomesHistoryScreen
import ru.hse.financialdetective.ui.feature.incomeshistory.viewmodel.IncomesHistoryViewModel

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
    }
}