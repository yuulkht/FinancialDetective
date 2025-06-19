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
import ru.hse.financialdetective.ui.components.organisms.IncomesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.theme.GreyDark
import ru.hse.financialdetective.ui.uimodel.model.IncomeUiModel
import ru.hse.financialdetective.ui.uimodel.model.IncomesWithTotalUiModel

val mockIncomesWithTotalUiModel = IncomesWithTotalUiModel(
    incomes = listOf(
        IncomeUiModel(id = 2, name = "Зарплата", amount = "100 000", currency = "₽"),
        IncomeUiModel(id = 3, name = "Подработка", amount = "50 000", currency = "₽"),
    ),
    total = "150 000",
    currency = "₽"
)

@Composable
fun IncomesScreen() {
    Box {
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
                amount = mockIncomesWithTotalUiModel.total,
                currency = mockIncomesWithTotalUiModel.currency
            )
            IncomesList(incomes = mockIncomesWithTotalUiModel.incomes)
        }
        AddButton(
            onClick = mockOnAddCLick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}