package ru.hse.financialdetective.ui.feature.expenseanalysis.screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.financialdetective.ui.theme.Typography
import ru.hse.financialdetective.ui.uimodel.model.CategoryForPieChartUiModel
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel

@Composable
fun PieChartWithLegend(
    data: List<CategoryForPieChartUiModel>,
    modifier: Modifier = Modifier,
    colors: List<Color> = PieChartColors,
    strokeWidth: Float = 34f,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            var startAngle = -90f
            data.forEachIndexed { i, category ->
                val sweepAngle = category.percent * 360f
                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth),
                    size = Size(size.width, size.height),
                    topLeft = Offset.Zero
                )
                startAngle += sweepAngle
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(start = 24.dp)
        ) {
            data.forEachIndexed { i, category ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Canvas(modifier = Modifier.size(9.dp)) {
                        drawCircle(color = colors[i])
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${(category.percent * 100).toInt()}% ${category.name}",
                        style = Typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewPieChartWithLegend() {
    val mockCategories = listOf(
        CategoryForPieChartUiModel(
            emoji = "🏠",
            name = "Аренда квартиры",
            amount = "25000",
            percent = 0.3f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(
            emoji = "👗",
            name = "Одежда",
            amount = "4500",
            percent = 0.2f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(

            emoji = "🐶",
            name = "На собачку",
            amount = "3200",
            percent = 0.4f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(

            emoji = "🛠",
            name = "Ремонт квартиры",
            amount = "18000",
            percent = 0.05f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(
            emoji = "🍭",
            name = "Продукты",
            amount = "7000",
            percent = 0.025f,
            currency = CurrencyUiModel.EUR
        ),
        CategoryForPieChartUiModel(

            emoji = "🏋️",
            name = "Спортзал",
            amount = "2500",
            percent = 0.025f,
            currency = CurrencyUiModel.EUR
        ),
    )

    PieChartWithLegend(
        data = mockCategories,
        modifier = Modifier.size(200.dp)
    )
}

val PieChartColors = listOf(
    Color(0xFF26EA7F),
    Color(0xFFFFEB3B),
    Color(0xFFFF5722),
    Color(0xFF3F51B5),
    Color(0xFFE91E63),
    Color(0xFF9C27B0),
    Color(0xFF00BCD4),
    Color(0xFF795548),
    Color(0xFF4CAF50),
    Color(0xFFFF9800),
    Color(0xFF2196F3),
    Color(0xFF607D8B),
    Color(0xFFCDDC39),
    Color(0xFF009688),
    Color(0xFF8BC34A),
    Color(0xFF673AB7),
    Color(0xFF03A9F4),
    Color(0xFFFFC107),
    Color(0xFFBA68C8),
    Color(0xFFA1887F),
    Color(0xFF90A4AE),
    Color(0xFF80CBC4),
    Color(0xFFB2DFDB),
    Color(0xFFFFAB91),
    Color(0xFFFFCC80),
    Color(0xFFE6EE9C),
    Color(0xFFCE93D8),
    Color(0xFFB0BEC5),
    Color(0xFFD7CCC8),
    Color(0xFFFFF59D)
)

