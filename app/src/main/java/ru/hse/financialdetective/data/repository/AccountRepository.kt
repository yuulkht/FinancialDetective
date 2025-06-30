package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.model.UpdateAccountDto
import ru.hse.financialdetective.domain.model.Account

interface AccountRepository {
    suspend fun getFirstAccount(): Result<Account>

    suspend fun updateAccount(
        accountId: Int,
        updateAccountDto: UpdateAccountDto
    ): Result<Account>
}