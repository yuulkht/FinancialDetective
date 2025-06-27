package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.mapper.toDomain
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.model.Account
import javax.inject.Inject

/**
 * Отвечает за вызов методов АПИ-сервиса, связанных с аккаунтом и обработку ответа
 */
class AccountRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getFirstAccount(): Result<Account> {
        return try {
            val response = api.getAccounts()

            when (response.code()) {
                200 -> {
                    val firstAccount = response.body()?.firstOrNull()
                        ?: return Result.failure(DataException(DataException.NO_ACCOUNTS))
                    Result.success(firstAccount.toDomain())
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
