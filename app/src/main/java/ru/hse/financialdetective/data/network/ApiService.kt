package ru.hse.financialdetective.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.hse.financialdetective.data.model.AccountResponse
import ru.hse.financialdetective.data.model.AccountsResponse
import ru.hse.financialdetective.data.model.TransactionsResponse

interface ApiService {

    // Получить счета пользователя
    @GET("/accounts")
    suspend fun getAccounts(): Response<AccountsResponse>

    // Получить информацию о счете по id
    @GET("/accounts/{id}")
    suspend fun getAccountById(@Path("id") id: Int): Response<AccountResponse>

    @GET("/transactions/account/{accountId}/period")
    suspend fun getTransactionsByAccountIdAndPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): Response<TransactionsResponse>
}