package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.domain.model.Account
import ru.hse.financialdetective.domain.repository.AccountRepository

/**
 * Отвечает за получение краткой информации об аккаунте
 */
class GetAccountInfoUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(): Result<Account> {
        val accountResponse = accountRepository.getFirstAccount()

        return accountResponse
    }
}
