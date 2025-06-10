package ru.hse.financialdetective.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.hse.coursework.financialdetective.ui.theme.GreenBright
import ru.hse.coursework.financialdetective.ui.theme.GreenLight
import ru.hse.coursework.financialdetective.ui.theme.GreyDark
import ru.hse.coursework.financialdetective.ui.theme.Surface

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
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp)
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
                        fontSize = 12.sp,
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