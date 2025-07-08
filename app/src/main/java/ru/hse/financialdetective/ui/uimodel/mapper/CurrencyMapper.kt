package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Currency
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel

fun Currency.toUiModel(): CurrencyUiModel {
    return when (this) {
        Currency.RUB -> CurrencyUiModel.RUB
        Currency.USD -> CurrencyUiModel.USD
        Currency.EUR -> CurrencyUiModel.EUR
    }
}