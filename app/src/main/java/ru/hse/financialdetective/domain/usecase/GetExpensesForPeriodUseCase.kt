package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.repository.AccountRepository
import ru.hse.financialdetective.data.repository.TransactionRepository
import ru.hse.financialdetective.domain.model.ExpensesWithTotal
import java.time.Instant
import java.time.ZoneOffset

/**
 * Отвечает за получение трат за период
 */
class GetExpensesForPeriodUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        dateFrom: Instant,
        dateTo: Instant
    ): Result<ExpensesWithTotal> {
        val accountResponse = accountRepository.getFirstAccount()
        if (accountResponse.isFailure) {
            return Result.failure(
                accountResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val accountId = accountResponse.getOrNull()?.id
            ?: return Result.failure(DataException(DataException.FAIL_TO_GET_ID))

        return transactionRepository.getExpensesForPeriod(
            accountId,
            dateFrom.atZone(ZoneOffset.UTC).toLocalDate().toString(),
            dateTo.atZone(ZoneOffset.UTC).toLocalDate().toString()
        )
    }
}

