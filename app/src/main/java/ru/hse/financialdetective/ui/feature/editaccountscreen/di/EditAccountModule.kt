package ru.hse.financialdetective.ui.feature.editaccountscreen.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.domain.usecase.UpdateAccountUseCase
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel
import ru.hse.financialdetective.ui.feature.editaccountscreen.di.EditAccountScope
import ru.hse.financialdetective.ui.feature.editaccountscreen.viewmodel.EditAccountViewModel

@Module
class EditAccountModule {

    @Provides
    @EditAccountScope
    fun provideGetAccountInfoUseCase(accountRepository: AccountRepository): GetAccountInfoUseCase {
        return GetAccountInfoUseCase(accountRepository)
    }

    @Provides
    @EditAccountScope
    fun provideUpdateAccountUseCase(accountRepository: AccountRepository): UpdateAccountUseCase {
        return UpdateAccountUseCase(accountRepository)
    }

    @Provides
    @EditAccountScope
    fun provideEditAccountViewModelFactory(
        getAccountInfoUseCase: GetAccountInfoUseCase,
        updateAccountUseCase: UpdateAccountUseCase,
    ): EditAccountViewModel.Factory {
        return EditAccountViewModel.Factory(getAccountInfoUseCase, updateAccountUseCase)
    }
}
