package ru.hse.financialdetective.domain.model

import ru.hse.coursework.financialdetective.R

enum class Currency(val label: String, val code: String, val symbol: String, val icon: Int) {
    RUB("Российский рубль", "RUB", "₽", R.drawable.mdi_ruble),
    USD("Американский доллар", "USD", "$", R.drawable.mdi_dollar),
    EUR("Евро", "EUR", "€", R.drawable.mdi_euro)
}
