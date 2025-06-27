package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.mapper.toDomain
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.model.Account
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getFirstAccount(): Result<Account> {
        return try {
            val response = api.getAccounts()

            when (response.code()) {
                200 -> {
                    val firstAccount = response.body()?.firstOrNull()
                        ?: return Result.failure(ApiException(ApiException.NO_ACCOUNTS))
                    Result.success(firstAccount.toDomain())
                }

                401 -> {
                    Result.failure(ApiException(ApiException.UNAUTHORIZED))
                }

                else -> {
                    Result.failure(ApiException("${ApiException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(ApiException(ApiException.SERVER_ERROR))
        }
    }
}
