package ru.hse.financialdetective.ui.navigation

import ru.hse.coursework.financialdetective.R

sealed class NavigationItem(val route: String, val icon: Int, val title: String) {
    data object Expenses : NavigationItem("expenses", R.drawable.expenses, "Расходы")
    data object Incomes : NavigationItem("incomes", R.drawable.incomes, "Доходы")
    data object ExpenseCategories :
        NavigationItem("expense_categories", R.drawable.expenses_categories, "Статьи")

    data object Settings : NavigationItem("settings", R.drawable.settings, "Настройки")
    data object Accounts : NavigationItem("accounts", R.drawable.accounts, "Счет")

    data object ExpensesHistory : NavigationItem("expenses_history", 0, "Моя история траты")
    data object IncomesHistory : NavigationItem("incomes_history", 0, "Моя история доходы")
}