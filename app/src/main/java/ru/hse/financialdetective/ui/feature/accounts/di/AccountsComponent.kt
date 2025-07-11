package ru.hse.financialdetective.ui.feature.accounts.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel

@AccountsScope
@Subcomponent(modules = [AccountsModule::class])
interface AccountsComponent {
    fun viewModelFactory(): AccountsViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountsComponent
    }
}
