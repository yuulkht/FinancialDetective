package ru.hse.financialdetective.ui.feature.expenses.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.domain.usecase.GetExpensesTodayUseCase
import ru.hse.financialdetective.ui.feature.accounts.di.AccountsScope
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel
import ru.hse.financialdetective.ui.feature.expenses.viewmodel.ExpensesViewModel

@Module
class ExpensesModule {

    @Provides
    @ExpensesScope
    fun provideGetExpensesTodayUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository): GetExpensesTodayUseCase
    {
        return GetExpensesTodayUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @ExpensesScope
    fun provideExpensesViewModelFactory(
        getExpensesTodayUseCase: GetExpensesTodayUseCase
    ): ExpensesViewModel.Factory {
        return ExpensesViewModel.Factory(getExpensesTodayUseCase)
    }
}
