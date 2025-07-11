package ru.hse.financialdetective.ui.feature.edittransaction.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.edittransaction.viewmodel.EditTransactionViewModel

@EditTransactionScope
@Subcomponent(modules = [EditTransactionModule::class])
interface EditTransactionComponent {
    fun viewModelFactory(): EditTransactionViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditTransactionComponent
    }
}
