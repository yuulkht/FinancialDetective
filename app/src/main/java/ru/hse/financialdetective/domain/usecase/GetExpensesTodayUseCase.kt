package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.repository.AccountRepository
import ru.hse.financialdetective.data.repository.ApiException
import ru.hse.financialdetective.data.repository.TransactionRepository
import ru.hse.financialdetective.domain.model.ExpensesWithTotal

class GetExpensesTodayUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
    ): Result<ExpensesWithTotal> {
        val accountResponse = accountRepository.getFirstAccount()
        if (accountResponse.isFailure) {
            return Result.failure(
                accountResponse.exceptionOrNull() ?: ApiException("Неизвестная ошибка")
            )
        }

        val accountId = accountResponse.getOrNull()?.id
            ?: return Result.failure(ApiException("Не удалось получить ID счета"))

        return transactionRepository.getExpensesForToday(accountId)
    }
}

