package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.model.UpdateAccountDto
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.mapper.todomain.toDomain
import ru.hse.financialdetective.domain.model.Account
import javax.inject.Inject

/**
 * Отвечает за вызов методов АПИ-сервиса, связанных с аккаунтом и обработку ответа
 */
class AccountRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AccountRepository {
    override suspend fun getFirstAccount(): Result<Account> {
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

    override suspend fun updateAccount(
        accountId: Int,
        updateAccountDto: UpdateAccountDto
    ): Result<Account> {
        return try {
            val response = api.updateAccount(
                accountId,
                updateAccountDto
            )

            when (response.code()) {
                200 -> {
                    val account = response.body() ?: return Result.failure(
                        DataException(
                            DataException.NO_ACCOUNTS
                        )
                    )
                    Result.success(account.toDomain())
                }

                400 -> {
                    Result.failure(DataException(DataException.INCORRECT_FORMAT))
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                404 -> {
                    Result.failure(DataException(DataException.NO_USER_ACCOUNT))
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
