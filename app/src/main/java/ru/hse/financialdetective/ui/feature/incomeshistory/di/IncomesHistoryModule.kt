package ru.hse.financialdetective.ui.feature.incomeshistory.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.GetIncomesForPeriodUseCase
import ru.hse.financialdetective.ui.feature.expenseshistory.di.IncomesHistoryScope
import ru.hse.financialdetective.ui.feature.incomeshistory.viewmodel.IncomesHistoryViewModel

@Module
class IncomesHistoryModule {

    @Provides
    @IncomesHistoryScope
    fun provideGetIncomesForPeriodUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository
    ): GetIncomesForPeriodUseCase {
        return GetIncomesForPeriodUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @IncomesHistoryScope
    fun provideIncomesHistoryViewModelFactory(
        getIncomesForPeriodUseCase: GetIncomesForPeriodUseCase
    ): IncomesHistoryViewModel.Factory {
        return IncomesHistoryViewModel.Factory(getIncomesForPeriodUseCase)
    }
}
