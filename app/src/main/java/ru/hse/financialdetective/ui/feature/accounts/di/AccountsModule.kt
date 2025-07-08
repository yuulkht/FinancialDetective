package ru.hse.financialdetective.ui.feature.accounts.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.di.AppScope
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel

@Module
class AccountsModule {

    @Provides
    @AccountsScope
    fun provideGetAccountInfoUseCase(accountRepository: AccountRepository): GetAccountInfoUseCase {
        return GetAccountInfoUseCase(accountRepository)
    }

    @Provides
    @AccountsScope
    fun provideAccountsViewModelFactory(
        getAccountInfoUseCase: GetAccountInfoUseCase
    ): AccountsViewModel.Factory {
        return AccountsViewModel.Factory(getAccountInfoUseCase)
    }
}
