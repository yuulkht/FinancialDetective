package ru.hse.financialdetective.domain.repository

import ru.hse.financialdetective.data.model.TransactionDtoRq
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.domain.model.Transaction

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
    ): Result<Transaction>

    suspend fun updateTransaction(
        transactionId: Int,
        transactionDto: TransactionDtoRq
    ): Result<Transaction>

    suspend fun deleteTransaction(
        transactionId: Int
    ): Result<String>
}