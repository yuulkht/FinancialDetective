package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.domain.model.Account

interface AccountRepository {
    suspend fun getFirstAccount(): Result<Account>
}