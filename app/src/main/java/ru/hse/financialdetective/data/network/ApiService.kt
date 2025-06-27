package ru.hse.financialdetective.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.hse.financialdetective.data.model.AccountDto
import ru.hse.financialdetective.data.model.AccountResponse
import ru.hse.financialdetective.data.model.TransactionResponse

interface ApiService {

    // Получить счета пользователя
    @GET("/api/v1/accounts")
    suspend fun getAccounts(): Response<List<AccountDto>>

    // Получить информацию о счете по id
    @GET("/api/v1/accounts/{id}")
    suspend fun getAccountById(@Path("id") id: Int): Response<AccountResponse>

    // Получить транзакции за период
    @GET("/api/v1/transactions/account/{accountId}/period")
    suspend fun getTransactionsByAccountIdAndPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): Response<List<TransactionResponse>>
}