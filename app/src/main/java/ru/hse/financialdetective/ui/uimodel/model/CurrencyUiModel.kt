package ru.hse.financialdetective.ui.uimodel.model

import ru.hse.coursework.financialdetective.R

enum class CurrencyUiModel(val label: String, val symbol: Int) {
    RUB("Российский рубль", R.drawable.mdi_ruble),
    USD("Американский доллар", R.drawable.mdi_dollar),
    EUR("Евро", R.drawable.mdi_euro)
}
