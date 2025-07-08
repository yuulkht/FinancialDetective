package ru.hse.financialdetective.data.di

import dagger.Binds
import dagger.Module
import ru.hse.financialdetective.data.repository.AccountRepositoryImpl
import ru.hse.financialdetective.domain.repository.CategoryRepository
import ru.hse.financialdetective.data.repository.CategoryRepositoryImpl
import ru.hse.financialdetective.data.repository.TransactionRepositoryImpl
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository

@Module
abstract class RepositoryModule {

    @Binds
    @DataScope
    abstract fun bindAccountRepository(
        impl: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    @DataScope
    abstract fun bindTransactionsRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    @DataScope
    abstract fun bindCategoriesRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository
}