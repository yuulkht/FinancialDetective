package ru.hse.financialdetective.ui.feature.editaccountscreen.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.editaccountscreen.viewmodel.EditAccountViewModel
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesModule

@EditAccountScope
@Subcomponent(modules = [EditAccountModule::class])
interface EditAccountComponent {
    fun viewModelFactory(): EditAccountViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditAccountComponent
    }
}
