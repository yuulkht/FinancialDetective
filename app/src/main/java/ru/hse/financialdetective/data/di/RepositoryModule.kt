package ru.hse.financialdetective.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.financialdetective.data.repository.AccountRepository
import ru.hse.financialdetective.data.repository.AccountRepositoryImpl
import ru.hse.financialdetective.data.repository.CategoryRepository
import ru.hse.financialdetective.data.repository.CategoryRepositoryImpl
import ru.hse.financialdetective.data.repository.TransactionRepository
import ru.hse.financialdetective.data.repository.TransactionRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAccountRepository(
        impl: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    @Singleton
    abstract fun bindTransactionsRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindCategoriesRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository
}