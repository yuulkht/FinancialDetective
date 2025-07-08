package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import ru.hse.financialdetective.domain.model.IncomesWithTotal
import ru.hse.financialdetective.domain.repository.TransactionRepository
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject

/**
 * Отвечает за вызов методов АПИ-сервиса, связанных с транзакциями по счету и обработку ответа
 */
class TransactionRepositoryImpl @Inject constructor(
    private val api: ApiService
) : TransactionRepository {
    override suspend fun getExpensesForToday(
        accountId: Int,
    ): Result<TransactionsResponse> {
        val today = Instant.now().atZone(ZoneOffset.systemDefault()).toLocalDate().toString()
        return getExpensesForPeriod(
            accountId,
            today,
            today
        )
    }

    override suspend fun getIncomesForToday(
        accountId: Int,
    ): Result<TransactionsResponse> {
        val today = Instant.now().atZone(ZoneOffset.systemDefault()).toLocalDate().toString()
        return getIncomesForPeriod(
            accountId,
            today,
            today
        )
    }

    override suspend fun getExpensesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<TransactionsResponse> {
        return try {
            val response = api.getTransactionsByAccountIdAndPeriod(
                accountId,
                dateFrom,
                dateTo
            )

            when (response.code()) {
                200 -> {
                    response.body()?.let {
                        Result.success(TransactionsResponse(it))
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

    override suspend fun getIncomesForPeriod(
        accountId: Int,
        dateFrom: String,
        dateTo: String
    ): Result<TransactionsResponse> {
        return try {
            val response = api.getTransactionsByAccountIdAndPeriod(
                accountId,
                dateFrom,
                dateTo
            )

            when (response.code()) {
                200 -> {
                    response.body()?.let {
                        Result.success(TransactionsResponse(it))
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