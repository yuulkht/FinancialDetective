package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.mapper.todto.toDto
import ru.hse.financialdetective.domain.model.CreateTransactionRequest
import ru.hse.financialdetective.domain.model.Transaction
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository

/**
 * Отвечает за создание транзакции
 */
class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(
        createTransactionRequest: CreateTransactionRequest
    ): Result<Transaction> {
        val accountResponse = accountRepository.getFirstAccount()
        if (accountResponse.isFailure) {
            return Result.failure(
                accountResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val accountId = accountResponse.getOrNull()?.id
            ?: return Result.failure(DataException(DataException.FAIL_TO_GET_ID))

        return transactionRepository.createTransaction(
            createTransactionRequest.toDto(accountId)
        )
    }
}
