package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.mapper.todto.toDto
import ru.hse.financialdetective.domain.model.Account
import ru.hse.financialdetective.domain.model.UpdateAccountRequest
import ru.hse.financialdetective.domain.repository.AccountRepository

/**
 * Отвечает за обновление информации об аккаунте
 */
class UpdateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(
        updateAccountRequest: UpdateAccountRequest
    ): Result<Account> {
        val accountResponse = accountRepository.getFirstAccount()
        if (accountResponse.isFailure) {
            return Result.failure(
                accountResponse.exceptionOrNull() ?: DataException(DataException.UNRECOGNIZED)
            )
        }

        val accountId = accountResponse.getOrNull()?.id
            ?: return Result.failure(DataException(DataException.FAIL_TO_GET_ID))

        return accountRepository.updateAccount(
            accountId,
            updateAccountRequest.toDto()
        )
    }
}
