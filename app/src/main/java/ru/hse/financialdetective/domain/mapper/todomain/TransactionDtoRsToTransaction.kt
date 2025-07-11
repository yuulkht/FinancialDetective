package ru.hse.financialdetective.domain.mapper.todomain

import ru.hse.financialdetective.data.model.TransactionDtoRs
import ru.hse.financialdetective.domain.model.Transaction

fun TransactionDtoRs.toDomain(): Transaction =
    Transaction(
        id = this.id,
        accountId = this.accountId,
        categoryId = this.categoryId,
        amount = this.amount.toDouble(),
        date = this.transactionDate,
        comment = this.comment ?: "",
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )