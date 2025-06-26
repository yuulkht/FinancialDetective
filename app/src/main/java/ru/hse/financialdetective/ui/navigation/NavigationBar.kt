package ru.hse.financialdetective.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreenLight
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.theme.Surface

// TODO сделать чтобы на второстепенных экранах нижний бар оставался активным (зеленым selected)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Expenses,
        NavigationItem.Incomes,
        NavigationItem.Accounts,
        NavigationItem.ExpenseCategories,
        NavigationItem.Settings,
    )

    NavigationBar(
        containerColor = Surface,
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (currentRoute == item.route) FontWeight.W600 else FontWeight.Medium
                        )
                    )

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GreenBright,
                    unselectedIconColor = GreyDark,
                    indicatorColor = GreenLight
                )
            )
        }
    }
}