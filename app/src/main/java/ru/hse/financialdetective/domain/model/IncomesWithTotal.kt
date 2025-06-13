package ru.hse.financialdetective.domain.model

data class IncomesWithTotal(
    val items: List<Income>
) {
    val totalAmount: Double = items.sumOf { it.amount }
    val currency: String = items.firstOrNull()?.currency ?: ""
}
