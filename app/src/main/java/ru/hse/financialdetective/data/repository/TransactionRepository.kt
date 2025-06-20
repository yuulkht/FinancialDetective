package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.mapper.toExpensesDomain
import ru.hse.financialdetective.data.mapper.toIncomesDomain
import ru.hse.financialdetective.data.model.TransactionsResponse
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import ru.hse.financialdetective.domain.model.IncomesWithTotal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getExpensesForToday(
        accountId: Int,
    ): Result<ExpensesWithTotal> {
        val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        return getExpensesForPeriod(
            accountId,
            today,
            today
        )
    }

    suspend fun getIncomesForToday(
        accountId: Int,
    ): Result<IncomesWithTotal> {
        val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
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
                    } ?: Result.failure(ApiException("Не удалось получить список транзакций"))
                }

                400 -> {
                    Result.failure(ApiException("Неверный формат ID счета или некорректный формат дат"))
                }

                401 -> {
                    Result.failure(ApiException("Неавторизованный доступ"))
                }

                else -> {
                    Result.failure(ApiException("Неизвестная ошибка: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(ApiException("Ошибка сети или сервера"))
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
                    } ?: Result.failure(ApiException("Не удалось получить список транзакций"))
                }

                400 -> {
                    Result.failure(ApiException("Неверный формат ID счета или некорректный формат дат"))
                }

                401 -> {
                    Result.failure(ApiException("Неавторизованный доступ"))
                }

                else -> {
                    Result.failure(ApiException("Неизвестная ошибка: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(ApiException("Ошибка сети или сервера"))
        }
    }
}