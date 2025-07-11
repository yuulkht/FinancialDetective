package ru.hse.financialdetective.ui.feature.configuretransaction.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.CreateTransactionUseCase
import ru.hse.financialdetective.domain.usecase.DeleteTransactionUseCase
import ru.hse.financialdetective.domain.usecase.UpdateTransactionUseCase
import ru.hse.financialdetective.ui.feature.configuretransaction.viewmodel.ConfigureTransactionViewModel

@Module
class ConfigureTransactionModule {

    @Provides
    @ConfigureTransactionScope
    fun provideCreateTransactionUseCase(
        transactionRepository: TransactionRepository,
        accountRepository: AccountRepository
    ): CreateTransactionUseCase {
        return CreateTransactionUseCase(transactionRepository, accountRepository)
    }

    @Provides
    @ConfigureTransactionScope
    fun provideUpdateTransactionUseCase(
        transactionRepository: TransactionRepository,
        accountRepository: AccountRepository
    ): UpdateTransactionUseCase {
        return UpdateTransactionUseCase(transactionRepository, accountRepository)
    }

    @Provides
    @ConfigureTransactionScope
    fun provideDeleteTransactionUseCase(transactionRepository: TransactionRepository): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(transactionRepository)
    }

    @Provides
    @ConfigureTransactionScope
    fun provideConfigureTransactionViewModelFactory(
        createTransactionUseCase: CreateTransactionUseCase,
        updateTransactionUseCase: UpdateTransactionUseCase,
        deleteTransactionUseCase: DeleteTransactionUseCase
    ): ConfigureTransactionViewModel.Factory {
        return ConfigureTransactionViewModel.Factory(
            createTransactionUseCase,
            updateTransactionUseCase,
            deleteTransactionUseCase
        )
    }
}
