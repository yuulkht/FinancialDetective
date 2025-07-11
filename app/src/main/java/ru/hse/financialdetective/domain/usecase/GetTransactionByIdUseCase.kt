package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.mapper.todomain.toDomainDetailed
import ru.hse.financialdetective.domain.model.TransactionDetailed
import ru.hse.financialdetective.domain.repository.TransactionRepository

/**
 * Отвечает за получение трат за период
 */
class GetTransactionByIdUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        transactionId: Int
    ): Result<TransactionDetailed> {
        val transactionResponse = transactionRepository.getTransactionById(
            transactionId
        )

        if (transactionResponse.isFailure) {
            return Result.failure(
                transactionResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val transaction = transactionResponse.getOrNull() ?: return Result.failure(
            DataException(
                DataException.NO_TRANSACTION
            )
        )

        return Result.success(transaction.toDomainDetailed())

    }
}

