package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.mapper.todomain.toExpensesDomain
import ru.hse.financialdetective.domain.model.Expenses
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository

/**
 * Отвечает за получение трат за сегодняшний день
 */
class GetExpensesTodayUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
    ): Result<Expenses> {
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

        val expensesResponse = transactionRepository.getExpensesForToday(accountId)

        if (expensesResponse.isFailure) {
            return Result.failure(
                expensesResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val expenses = expensesResponse.getOrNull() ?: return Result.failure(
            DataException(
                DataException.NO_TRANSACTIONS
            )
        )

        return Result.success(expenses.toExpensesDomain(currency))
    }
}

