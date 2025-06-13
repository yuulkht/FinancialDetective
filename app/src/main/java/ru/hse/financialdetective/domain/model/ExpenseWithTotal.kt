package ru.hse.financialdetective.domain.model

data class ExpenseWithTotal(
    val items: List<Expense>
) {
    val totalAmount: Double = items.sumOf { it.amount }
    val currency: String = items.firstOrNull()?.currency ?: ""
}
