package ru.hse.financialdetective.domain.usecase

import ru.hse.financialdetective.data.model.AccountsResponse
import ru.hse.financialdetective.data.network.ApiCallResult
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.data.network.SafeApiCaller
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(
    private val api: ApiService,
    private val safeApiCaller: SafeApiCaller,
) {
    suspend fun execute(): ApiCallResult<AccountsResponse> {
        return safeApiCaller.safeApiCall {
            api.getAccounts()
        }
    }
}
