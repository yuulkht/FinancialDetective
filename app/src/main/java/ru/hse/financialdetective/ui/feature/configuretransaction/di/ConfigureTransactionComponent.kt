package ru.hse.financialdetective.ui.feature.configuretransaction.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.configuretransaction.viewmodel.ConfigureTransactionViewModel

@ConfigureTransactionScope
@Subcomponent(modules = [ConfigureTransactionModule::class])
interface ConfigureTransactionComponent {
    fun viewModelFactory(): ConfigureTransactionViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ConfigureTransactionComponent
    }
}
