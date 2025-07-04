package ru.hse.financialdetective.data.repository

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
}