package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.domain.model.Transaction

fun TransactionResponse.toDomain(): Transaction =
    Transaction(
        id = this.id,
        accountId = this.account.id,
        categoryId = this.categoryDto.id,
        amount = this.amount.toDouble(),
        date = this.transactionDate,
        comment = this.comment ?: "",
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )