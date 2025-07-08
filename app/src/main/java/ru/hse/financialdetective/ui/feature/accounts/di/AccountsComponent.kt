package ru.hse.financialdetective.ui.feature.accounts.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.accounts.viewmodel.AccountsViewModel
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesComponent
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesModule

@AccountsScope
@Subcomponent(modules = [IncomesModule::class])
interface AccountsComponent {
    fun viewModelFactory(): AccountsViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): IncomesComponent
    }
}
