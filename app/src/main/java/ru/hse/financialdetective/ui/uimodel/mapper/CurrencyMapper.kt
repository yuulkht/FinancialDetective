package ru.hse.financialdetective.ui.uimodel.mapper

fun getCurrencySymbol(currency: String): String {
    return when (currency) {
        "RUB" -> "₽"
        "USD" -> "$"
        "EUR" -> "€"
        else -> "Unknown"
    }
}