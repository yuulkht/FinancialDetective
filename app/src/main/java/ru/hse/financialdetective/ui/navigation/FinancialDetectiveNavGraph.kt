package ru.hse.financialdetective.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.hse.financialdetective.ui.screen.ExpenseCategoriesScreen
import ru.hse.financialdetective.ui.screen.SettingsScreen
import ru.hse.financialdetective.ui.screen.accounts.AccountsScreen
import ru.hse.financialdetective.ui.screen.expenses.ExpensesScreen
import ru.hse.financialdetective.ui.screen.expenseshistory.ExpensesHistoryScreen
import ru.hse.financialdetective.ui.screen.incomes.IncomesScreen
import ru.hse.financialdetective.ui.screen.incomeshistory.IncomesHistoryScreen

@Composable
fun FinancialDetectiveNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Expenses.route
    ) {
        composable(NavigationItem.Expenses.route) {
            ExpensesScreen(navController)
        }
        composable(NavigationItem.Incomes.route) {
            IncomesScreen(navController)
        }
        composable(NavigationItem.Accounts.route) {
            AccountsScreen(navController)
        }
        composable(NavigationItem.ExpenseCategories.route) {
            ExpenseCategoriesScreen(navController)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(navController)
        }
        composable(NavigationItem.IncomesHistory.route) {
            IncomesHistoryScreen(navController)
        }
        composable(NavigationItem.ExpensesHistory.route) {
            ExpensesHistoryScreen(navController)
        }
    }
}