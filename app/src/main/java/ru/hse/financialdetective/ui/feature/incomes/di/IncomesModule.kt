package ru.hse.financialdetective.ui.feature.incomes.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.GetIncomesTodayUseCase
import ru.hse.financialdetective.ui.feature.incomes.viewmodel.IncomesViewModel

@Module
class IncomesModule {

    @Provides
    @IncomesScope
    fun provideGetIncomesTodayUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository
    ): GetIncomesTodayUseCase {
        return GetIncomesTodayUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @IncomesScope
    fun provideIncomesViewModelFactory(
        getIncomesTodayUseCase: GetIncomesTodayUseCase
    ): IncomesViewModel.Factory {
        return IncomesViewModel.Factory(getIncomesTodayUseCase)
    }
}
