package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.domain.model.Currency

fun String.toCurrencyDomain(): Currency {
    return when (this) {
        "RUB" -> Currency.RUB
        "USD" -> Currency.USD
        "EUR" -> Currency.EUR
        else -> Currency.RUB
    }
}