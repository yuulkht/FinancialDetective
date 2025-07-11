package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.model.TransactionDtoRq
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.mapper.todomain.toDomain
import ru.hse.financialdetective.domain.model.Transaction
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

    override suspend fun createTransaction(
        transactionDto: TransactionDtoRq
    ): Result<Transaction> {
        return try {
            val response = api.createTransaction(transactionDto)

            when (response.code()) {
                200 -> {
                    val transaction = response.body() ?: return Result.failure(
                        DataException(
                            DataException.INCORRECT_TRANSACTION
                        )
                    )
                    Result.success(transaction.toDomain())
                }

                400 -> {
                    Result.failure(DataException(DataException.INCORRECT_FORMAT))
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                404 -> {
                    Result.failure(DataException(DataException.NO_USER_ACCOUNT_OR_CATEGORY))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }

    override suspend fun updateTransaction(
        transactionId: Int,
        transactionDto: TransactionDtoRq
    ): Result<Transaction> {
        return try {
            val response = api.updateTransaction(
                transactionId,
                transactionDto
            )

            when (response.code()) {
                200 -> {
                    val transaction = response.body() ?: return Result.failure(
                        DataException(
                            DataException.INCORRECT_TRANSACTION
                        )
                    )
                    Result.success(transaction.toDomain())
                }

                400 -> {
                    Result.failure(DataException(DataException.INCORRECT_FORMAT))
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                404 -> {
                    Result.failure(DataException(DataException.NO_TRANSACTION_USER_ACCOUNT_OR_CATEGORY))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }

    override suspend fun deleteTransaction(transactionId: Int): Result<String> {
        return try {
            val response = api.deleteTransaction(
                transactionId
            )

            when (response.code()) {
                204 -> {
                    Result.success(response.body() ?: "")
                }

                400 -> {
                    Result.failure(DataException(DataException.INCORRECT_FORMAT))
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                404 -> {
                    Result.failure(DataException(DataException.NO_TRANSACTION))
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