package ru.hse.financialdetective.ui.navigation

import ru.hse.coursework.financialdetective.R

/**
 * Отвечает за навигационные элементы приложения
 */
sealed class NavigationItem(val route: String, val icon: Int, val title: String) {
    data object Expenses : NavigationItem("expenses", R.drawable.expenses, "Расходы")
    data object Incomes : NavigationItem("incomes", R.drawable.incomes, "Доходы")

    data object EditTransaction : NavigationItem("edit_transaction", 0, "Изменить транзакцию")


    data object ExpenseCategories :
        NavigationItem("categories", R.drawable.expenses_categories, "Статьи")

    data object Settings : NavigationItem("settings", R.drawable.settings, "Настройки")

    data object Accounts : NavigationItem("account", R.drawable.accounts, "Счет")
    data object EditAccount : NavigationItem("account_edit", 0, "Редактировать счет")

    data object ExpensesHistory : NavigationItem("expenses_history", 0, "Моя история траты")
    data object IncomesHistory : NavigationItem("incomes_history", 0, "Моя история доходы")
}