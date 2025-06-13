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
import ru.hse.coursework.financialdetective.ui.theme.GreenBright
import ru.hse.coursework.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.domain.model.Income
import ru.hse.financialdetective.ui.components.molecules.AddButton
import ru.hse.financialdetective.ui.components.molecules.TransactionsInfoItem
import ru.hse.financialdetective.ui.components.organisms.IncomesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader

@Composable
fun IncomesScreen() {
    val mockIncomes = listOf(
        Income(id = 2, name = "Работа", amount = "100 000", currency = "₽"),
        Income(id = 3, name = "Подработка", amount = "50 000", currency = "₽"),
    )

    val onAddCLick = {}

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScreenHeader(
                title = "Доходы сегодня",
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
                amount = "150 000",
                currency = "₽"
            )
            IncomesList(incomes = mockIncomes)
        }
        AddButton(
            onClick = onAddCLick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}