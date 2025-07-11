package ru.hse.financialdetective.domain.repository

import ru.hse.financialdetective.data.model.TransactionDtoRq
import ru.hse.financialdetective.data.model.TransactionDtoRs
import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.data.model.TransactionsResponse

interface TransactionRepository {
    suspend fun getExpensesForToday(accountId: Int): Result<TransactionsResponse>
    suspend fun getIncomesForToday(accountId: Int): Result<TransactionsResponse>
    suspend fun getExpensesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<TransactionsResponse>

    suspend fun getIncomesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<TransactionsResponse>

    suspend fun createTransaction(
        transactionDto: TransactionDtoRq
    ): Result<TransactionDtoRs>

    suspend fun updateTransaction(
        transactionId: Int,
        transactionDto: TransactionDtoRq
    ): Result<TransactionResponse>
}