package ru.hse.financialdetective.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.hse.financialdetective.ui.screen.AccountsScreen
import ru.hse.financialdetective.ui.screen.ExpenseCategoriesScreen
import ru.hse.financialdetective.ui.screen.SettingsScreen
import ru.hse.financialdetective.ui.screen.expenses.ExpensesScreen
import ru.hse.financialdetective.ui.screen.incomes.IncomesScreen

@Composable
fun FinancialDetectiveNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Expenses.route
    ) {
        composable(NavigationItem.Expenses.route) {
            ExpensesScreen()
        }
        composable(NavigationItem.Incomes.route) {
            IncomesScreen()
        }
        composable(NavigationItem.Accounts.route) {
            AccountsScreen()
        }
        composable(NavigationItem.ExpenseCategories.route) {
            ExpenseCategoriesScreen()
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen()
        }
    }
}