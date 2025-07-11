package ru.hse.financialdetective.ui.feature.edittransaction.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.CategoryRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.CreateTransactionUseCase
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.domain.usecase.GetCategoriesByTypeUseCase
import ru.hse.financialdetective.ui.feature.createtransaction.viewmodel.CreateTransactionViewModel

@Module
class CreateTransactionModule {

    @Provides
    @CreateTransactionScope
    fun provideGetAccountUseCase(
        accountRepository: AccountRepository
    ): GetAccountInfoUseCase {
        return GetAccountInfoUseCase(accountRepository)
    }

    @Provides
    @CreateTransactionScope
    fun provideCreateTransactionUseCase(
        transactionRepository: TransactionRepository,
        accountRepository: AccountRepository
    ): CreateTransactionUseCase {
        return CreateTransactionUseCase(transactionRepository, accountRepository)
    }

    @Provides
    @CreateTransactionScope
    fun provideGetCategoriesUseCase(
        categoryRepository: CategoryRepository,
    ): GetCategoriesByTypeUseCase {
        return GetCategoriesByTypeUseCase(categoryRepository)
    }

    @Provides
    @CreateTransactionScope
    fun provideCreateTransactionViewModelFactory(
        getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
        createTransactionUseCase: CreateTransactionUseCase,
        getAccountInfoUseCase: GetAccountInfoUseCase
    ): CreateTransactionViewModel.Factory {
        return CreateTransactionViewModel.Factory(
            getCategoriesByTypeUseCase,
            createTransactionUseCase,
            getAccountInfoUseCase
        )
    }
}
