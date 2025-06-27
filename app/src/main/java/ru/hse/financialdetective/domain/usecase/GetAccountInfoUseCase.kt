package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.repository.AccountRepository
import ru.hse.financialdetective.domain.model.Account

class GetAccountInfoUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(): Result<Account> {
        val accountResponse = accountRepository.getFirstAccount()

        return accountResponse
    }
}
