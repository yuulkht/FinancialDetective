package ru.hse.financialdetective.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.components.molecules.AddButton
import ru.hse.financialdetective.ui.components.molecules.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.ExpensesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.ExpenseUiModel
import ru.hse.financialdetective.ui.uimodel.model.ExpensesWithTotalUiModel

// Моковые данные
val mockExpensesWithTotal = ExpensesWithTotalUiModel(
    expenses = listOf(
        ExpenseUiModel(
            id = 1,
            emoji = "🏠",
            category = "Аренда квартиры",
            amount = "25000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        ExpenseUiModel(
            id = 2, emoji = "👗", category = "Одежда", amount = "4500", comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        ExpenseUiModel(
            id = 3,
            emoji = "🐶",
            category = "На собачку",
            amount = "3200",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        ExpenseUiModel(
            id = 4,
            emoji = "🛠",
            category = "Ремонт квартиры",
            amount = "18000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        ExpenseUiModel(
            id = 5,
            emoji = "🍭",
            category = "Продукты",
            amount = "7000",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        ExpenseUiModel(
            id = 6,
            emoji = "🏋️",
            category = "Спортзал",
            amount = "2500",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        ),
        ExpenseUiModel(
            id = 7,
            emoji = "💊",
            category = "Медицина",
            amount = "5200",
            comment = "",
            date = "19:02, 20.06.2025",
            currency = "₽"
        )
    ),
    total = "435 000",
    currency = "₽"
)

@Composable
fun ExpensesScreen() {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScreenHeader(
                title = "Расходы сегодня",
                tailIcon = {
                    Icon(
                        painter = painterResource(R.drawable.history),
                        contentDescription = "История",
                        modifier = Modifier
                            .size(48.dp),
                        tint = GreyDark
                    )
                },
                color = GreenBright
            )
            TransactionsInfoItem(
                amount = mockExpensesWithTotal.total,
                currency = mockExpensesWithTotal.currency
            )
            ExpensesList(expenses = mockExpensesWithTotal.expenses)
        }
        AddButton(
            onClick = mockOnAddCLick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}