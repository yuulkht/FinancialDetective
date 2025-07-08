package ru.hse.financialdetective.ui.feature.expenseshistory.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.GetExpensesForPeriodUseCase
import ru.hse.financialdetective.ui.feature.expenseshistory.viewmodel.ExpensesHistoryViewModel

@Module
class ExpensesHistoryModule {

    @Provides
    @ExpensesHistoryScope
    fun provideGetExpensesForPeriodUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository): GetExpensesForPeriodUseCase
    {
        return GetExpensesForPeriodUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @ExpensesHistoryScope
    fun provideExpensesHistoryViewModelFactory(
        getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase
    ): ExpensesHistoryViewModel.Factory {
        return ExpensesHistoryViewModel.Factory(getExpensesForPeriodUseCase)
    }
}
