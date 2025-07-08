package ru.hse.financialdetective.ui.feature.accounts.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel
import ru.hse.financialdetective.ui.feature.editaccountscreen.di.EditAccountScope

@Module
class AccountsModule {

    @Provides
    @EditAccountScope
    fun provideGetAccountInfoUseCase(accountRepository: AccountRepository): GetAccountInfoUseCase {
        return GetAccountInfoUseCase(accountRepository)
    }

    @Provides
    @EditAccountScope
    fun provideAccountsViewModelFactory(
        getAccountInfoUseCase: GetAccountInfoUseCase
    ): AccountsViewModel.Factory {
        return AccountsViewModel.Factory(getAccountInfoUseCase)
    }
}
