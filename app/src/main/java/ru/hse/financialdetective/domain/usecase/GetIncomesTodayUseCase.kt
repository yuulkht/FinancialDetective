package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.repository.AccountRepository
import ru.hse.financialdetective.data.repository.TransactionRepository
import ru.hse.financialdetective.domain.mapper.todomain.toIncomesDomain
import ru.hse.financialdetective.domain.model.Incomes

/**
 * Отвечает за получение поступлений за сегодняшний день
 */
class GetIncomesTodayUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
    ): Result<Incomes> {
        val accountResponse = accountRepository.getFirstAccount()
        if (accountResponse.isFailure) {
            return Result.failure(
                accountResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val accountId = accountResponse.getOrNull()?.id
            ?: return Result.failure(DataException(DataException.FAIL_TO_GET_ID))
        val currency = accountResponse.getOrNull()?.currency
            ?: return Result.failure(DataException(DataException.FAIL_TO_GET_CURRENCY))


        val incomesResponse = transactionRepository.getIncomesForToday(accountId)

        if (incomesResponse.isFailure) {
            return Result.failure(
                incomesResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val incomes = incomesResponse.getOrNull() ?: return Result.failure(
            DataException(
                DataException.NO_TRANSACTIONS
            )
        )

        return Result.success(incomes.toIncomesDomain(currency))
    }
}

