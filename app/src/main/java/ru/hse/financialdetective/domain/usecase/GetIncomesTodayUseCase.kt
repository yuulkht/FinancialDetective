package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.repository.AccountRepository
import ru.hse.financialdetective.data.repository.TransactionRepository
import ru.hse.financialdetective.domain.model.IncomesWithTotal

/**
 * Отвечает за получение поступлений за сегодняшний день
 */
class GetIncomesTodayUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
    ): Result<IncomesWithTotal> {
        val accountResponse = accountRepository.getFirstAccount()
        if (accountResponse.isFailure) {
            return Result.failure(
                accountResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val accountId = accountResponse.getOrNull()?.id
            ?: return Result.failure(DataException(DataException.FAIL_TO_GET_ID))

        return transactionRepository.getIncomesForToday(accountId)
    }
}

