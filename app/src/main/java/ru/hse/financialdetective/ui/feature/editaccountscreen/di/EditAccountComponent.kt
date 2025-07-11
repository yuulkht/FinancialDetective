package ru.hse.financialdetective.ui.feature.editaccountscreen.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.editaccountscreen.viewmodel.EditAccountViewModel

@EditAccountScope
@Subcomponent(modules = [EditAccountModule::class])
interface EditAccountComponent {
    fun viewModelFactory(): EditAccountViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditAccountComponent
    }
}
