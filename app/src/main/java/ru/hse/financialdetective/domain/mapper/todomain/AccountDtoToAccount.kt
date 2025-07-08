package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.AccountDto
import ru.hse.financialdetective.domain.model.Account

fun AccountDto.toDomain(): Account =
    Account(
        id = this.id,
        name = this.name,
        balance = this.balance,
        currency = this.currency.toCurrencyDomain()
    )