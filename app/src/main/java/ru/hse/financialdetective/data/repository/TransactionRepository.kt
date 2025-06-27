package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import ru.hse.financialdetective.domain.model.IncomesWithTotal

interface TransactionRepository {
    suspend fun getExpensesForToday(accountId: Int): Result<ExpensesWithTotal>
    suspend fun getIncomesForToday(accountId: Int): Result<IncomesWithTotal>
    suspend fun getExpensesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<ExpensesWithTotal>

    suspend fun getIncomesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<IncomesWithTotal>
}