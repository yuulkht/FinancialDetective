package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.domain.model.TransactionDetailed

fun TransactionResponse.toDomainDetailed(): TransactionDetailed =
    TransactionDetailed(
        id = this.id,
        emoji = this.categoryDto.emoji,
        category = this.categoryDto.name,
        amount = this.amount.toDouble(),
        date = this.transactionDate,
        comment = this.comment ?: "",
        currency = this.account.currency.toCurrencyDomain()
    )