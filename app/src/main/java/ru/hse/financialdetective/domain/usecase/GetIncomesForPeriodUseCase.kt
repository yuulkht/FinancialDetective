package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.mapper.todomain.toIncomesDomain
import ru.hse.financialdetective.domain.model.Incomes
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import java.time.Instant
import java.time.ZoneOffset

/**
 * Отвечает за получение поступлений за период
 */
class GetIncomesForPeriodUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        dateFrom: Instant,
        dateTo: Instant
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

        val incomesResponse = transactionRepository.getIncomesForPeriod(
            accountId,
            dateFrom.atZone(ZoneOffset.UTC).toLocalDate().toString(),
            dateTo.atZone(ZoneOffset.UTC).toLocalDate().toString()
        )

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

