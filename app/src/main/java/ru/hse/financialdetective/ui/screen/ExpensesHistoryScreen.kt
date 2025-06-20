//package ru.hse.financialdetective.ui.screen
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import ru.hse.coursework.financialdetective.R
//import ru.hse.financialdetective.ui.components.molecules.AddButton
//import ru.hse.financialdetective.ui.components.molecules.TransactionsInfoItem
//import ru.hse.financialdetective.ui.components.organisms.ExpensesList
//import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
//import ru.hse.financialdetective.ui.theme.GreenBright
//import ru.hse.financialdetective.ui.theme.GreyDark
//
//@Composable
//fun ExpensesHistoryScreen() {
//    Box {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            ScreenHeader(
//                title = "Расходы сегодня",
//                tailIcon = {
//                    Icon(
//                        painter = painterResource(R.drawable.history),
//                        contentDescription = "История",
//                        modifier = Modifier
//                            .size(48.dp),
//                        tint = GreyDark
//                    )
//                },
//                color = GreenBright
//            )
//            TransactionsInfoItem(
//                amount = mockExpensesWithTotal.total,
//                currency = mockExpensesWithTotal.currency
//            )
//            ExpensesList(expenses = mockExpensesWithTotal.expenses)
//        }
//        AddButton(
//            onClick = mockOnAddCLick,
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//        )
//    }
//}