package ru.hse.financialdetective.domain.domainmodel.mapper

import ru.hse.financialdetective.data.model.AccountDto
import ru.hse.financialdetective.domain.domainmodel.model.Account

fun AccountDto.toDomain(): Account =
    Account(
        id = this.id,
        name = this.name,
        balance = this.balance,
        currency = this.currency
    )