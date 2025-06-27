package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.mapper.toExpensesDomain
import ru.hse.financialdetective.data.mapper.toIncomesDomain
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import ru.hse.financialdetective.domain.model.IncomesWithTotal
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getExpensesForToday(
        accountId: Int,
    ): Result<ExpensesWithTotal> {
        val today = Instant.now().atZone(ZoneOffset.systemDefault()).toLocalDate().toString()
        return getExpensesForPeriod(
            accountId,
            today,
            today
        )
    }

    suspend fun getIncomesForToday(
        accountId: Int,
    ): Result<IncomesWithTotal> {
        val today = Instant.now().atZone(ZoneOffset.systemDefault()).toLocalDate().toString()
        return getIncomesForPeriod(
            accountId,
            today,
            today
        )
    }

    suspend fun getExpensesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<ExpensesWithTotal> {
        return try {
            val response = api.getTransactionsByAccountIdAndPeriod(
                accountId,
                dateFrom,
                dateTo
            )

            when (response.code()) {
                200 -> {
                    response.body()?.let {
                        Result.success(TransactionsResponse(it).toExpensesDomain())
                    } ?: Result.failure(DataException(DataException.NO_TRANSACTIONS))
                }

                400 -> {
                    Result.failure(DataException(DataException.INCORRECT_FORMAT))
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }

    suspend fun getIncomesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<IncomesWithTotal> {
        return try {
            val response = api.getTransactionsByAccountIdAndPeriod(
                accountId,
                dateFrom,
                dateTo
            )

            when (response.code()) {
                200 -> {
                    response.body()?.let {
                        Result.success(TransactionsResponse(it).toIncomesDomain())
                    } ?: Result.failure(DataException(DataException.NO_TRANSACTIONS))
                }

                400 -> {
                    Result.failure(DataException(DataException.INCORRECT_FORMAT))
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }
}