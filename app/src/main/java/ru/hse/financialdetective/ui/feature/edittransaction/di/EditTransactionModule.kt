package ru.hse.financialdetective.ui.feature.edittransaction.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.CategoryRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.DeleteTransactionUseCase
import ru.hse.financialdetective.domain.usecase.GetCategoriesByTypeUseCase
import ru.hse.financialdetective.domain.usecase.GetTransactionByIdUseCase
import ru.hse.financialdetective.domain.usecase.UpdateTransactionUseCase
import ru.hse.financialdetective.ui.feature.edittransaction.viewmodel.EditTransactionViewModel

@Module
class EditTransactionModule {

    @Provides
    @EditTransactionScope
    fun provideGetCategoriesUseCase(
        categoryRepository: CategoryRepository,
    ): GetCategoriesByTypeUseCase {
        return GetCategoriesByTypeUseCase(categoryRepository)
    }

    @Provides
    @EditTransactionScope
    fun provideGetTransactionUseCase(
        transactionRepository: TransactionRepository,
    ): GetTransactionByIdUseCase {
        return GetTransactionByIdUseCase(transactionRepository)
    }

    @Provides
    @EditTransactionScope
    fun provideUpdateTransactionUseCase(
        transactionRepository: TransactionRepository,
        accountRepository: AccountRepository
    ): UpdateTransactionUseCase {
        return UpdateTransactionUseCase(transactionRepository, accountRepository)
    }

    @Provides
    @EditTransactionScope
    fun provideDeleteTransactionUseCase(transactionRepository: TransactionRepository): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(transactionRepository)
    }

    @Provides
    @EditTransactionScope
    fun provideConfigureTransactionViewModelFactory(
        getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
        getTransactionByIdUseCase: GetTransactionByIdUseCase,
        updateTransactionUseCase: UpdateTransactionUseCase,
        deleteTransactionUseCase: DeleteTransactionUseCase
    ): EditTransactionViewModel.Factory {
        return EditTransactionViewModel.Factory(
            getCategoriesByTypeUseCase,
            getTransactionByIdUseCase,
            updateTransactionUseCase,
            deleteTransactionUseCase
        )
    }
}
