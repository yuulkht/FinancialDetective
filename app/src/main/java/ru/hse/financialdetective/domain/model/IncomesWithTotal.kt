package ru.hse.financialdetective.domain.model

import ru.hse.financialdetective.ui.uimodel.mapper.getCurrencySymbol

data class IncomesWithTotal(
    val items: List<Income>
) {
    val totalAmount: Double = items.sumOf { it.amount }
    val currency: String
        get() = items.firstOrNull()?.currency?.let { currency ->
            getCurrencySymbol(currency)
        } ?: "₽"
}
