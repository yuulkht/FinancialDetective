package ru.hse.financialdetective.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import ru.hse.financialdetective.data.model.AccountDto
import ru.hse.financialdetective.data.model.AccountResponse
import ru.hse.financialdetective.data.model.CategoryDto
import ru.hse.financialdetective.data.model.TransactionDtoRq
import ru.hse.financialdetective.data.model.TransactionDtoRs
import ru.hse.financialdetective.data.model.TransactionResponse
import ru.hse.financialdetective.data.model.UpdateAccountDto

/**
 * Отвечает за взаимодействие с бэкендом по REST API
 */
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

    // Получить информацию о категориях
    @GET("/api/v1/categories")
    suspend fun getCategories(): Response<List<CategoryDto>>

    // Получить информацию о категориях по типу
    @GET("/api/v1/categories/type/{isIncome}")
    suspend fun getCategoriesByType(
        @Path("isIncome") isIncome: Boolean
    ): Response<List<CategoryDto>>

    // Обновить данные аккаунта
    @PUT("/api/v1/accounts/{id}")
    suspend fun updateAccount(
        @Path("id") accountId: Int,
        @Body updateAccountDto: UpdateAccountDto
    ): Response<AccountDto>

    // Создать транзакцию
    @POST("/api/v1/transactions")
    suspend fun createTransaction(
        @Body transactionDtoRq: TransactionDtoRq
    ): Response<TransactionDtoRs>

    // Обновить транзакцию
    @PUT("/api/v1/transactions{id}")
    suspend fun updateTransaction(
        @Path("id") id: Int,
        @Body transactionDtoRq: TransactionDtoRq
    ): Response<TransactionResponse>

    // Удалить транзакцию
    @DELETE("/api/v1/transactions{id}")
    suspend fun deleteTransaction(
        @Path("id") id: Int
    ): Response<String>

    // Получить транзакцию
    @GET("/api/v1/transactions{id}")
    suspend fun getTransaction(
        @Path("id") id: Int
    ): Response<TransactionResponse>

}