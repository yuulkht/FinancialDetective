package ru.hse.financialdetective.domain.mapper.todto

import ru.hse.financialdetective.data.model.TransactionDtoRq
import ru.hse.financialdetective.domain.model.CreateTransactionRequest

// TODO Возможно с временем будет проблема
fun CreateTransactionRequest.toDto(accountId: Int): TransactionDtoRq =
    TransactionDtoRq(
        accountId = accountId,
        categoryId = this.categoryId,
        amount = this.amount,
        transactionDate = this.transactionDate,
        comment = this.comment
    )