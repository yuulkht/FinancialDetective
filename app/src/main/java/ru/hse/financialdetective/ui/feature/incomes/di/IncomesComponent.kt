package ru.hse.financialdetective.ui.feature.incomes.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.accounts.di.AccountsScope
import ru.hse.financialdetective.ui.feature.incomes.viewmodel.IncomesViewModel

@IncomesScope
@Subcomponent(modules = [IncomesModule::class])
interface IncomesComponent {
    fun viewModelFactory(): IncomesViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): IncomesComponent
    }
}
