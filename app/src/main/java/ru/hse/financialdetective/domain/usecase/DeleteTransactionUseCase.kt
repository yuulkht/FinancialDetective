package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.domain.repository.TransactionRepository

/**
 * Отвечает за удаление транзакции
 */
class DeleteTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        transactionId: Int
    ): Result<String> {
        return transactionRepository.deleteTransaction(
            transactionId
        )
    }
}
