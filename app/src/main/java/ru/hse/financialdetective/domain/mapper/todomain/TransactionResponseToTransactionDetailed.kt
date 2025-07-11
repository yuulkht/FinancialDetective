package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.domain.model.TransactionDetailed

fun TransactionResponse.toDomainDetailed(): TransactionDetailed =
    TransactionDetailed(
        id = this.id,
        account = this.account.toDomain(),
        emoji = this.categoryDto.emoji,
        category = this.categoryDto.toDomain(),
        amount = this.amount.toDouble(),
        date = this.transactionDate,
        comment = this.comment ?: "",
        currency = this.account.currency.toCurrencyDomain()
    )