package ru.hse.financialdetective.domain.mapper.todto

import ru.hse.financialdetective.data.model.UpdateAccountDto
import ru.hse.financialdetective.domain.model.UpdateAccountRequest

fun UpdateAccountRequest.toDto(): UpdateAccountDto =
    UpdateAccountDto(
        name = this.name,
        balance = this.balance,
        currency = this.currency
    )