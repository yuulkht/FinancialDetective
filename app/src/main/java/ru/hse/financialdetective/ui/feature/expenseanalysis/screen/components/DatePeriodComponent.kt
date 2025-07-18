package ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.uimodel.mapper.formatLocalDate
import java.time.LocalDate

@Composable
fun DatePeriodComponent(
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = GreenBright,
                shape = RoundedCornerShape(100)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = formatLocalDate(date),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
@Preview(showBackground = true, apiLevel = 34)
fun DatePeriodComponentPreview() {
    DatePeriodComponent(LocalDate.now())
}