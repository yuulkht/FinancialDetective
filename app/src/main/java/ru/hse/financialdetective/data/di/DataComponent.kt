package ru.hse.financialdetective.data.di

import dagger.Subcomponent
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository

@DataScope
@Subcomponent(
    modules = [
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface DataComponent {

    fun accountRepository(): AccountRepository
    fun transactionRepository(): TransactionRepository
    fun apiService(): ApiService

    @Subcomponent.Factory
    interface Factory {
        fun create(): DataComponent
    }
}
