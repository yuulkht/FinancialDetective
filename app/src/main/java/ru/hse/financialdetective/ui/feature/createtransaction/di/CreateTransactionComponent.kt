package ru.hse.financialdetective.ui.feature.createtransaction.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.createtransaction.viewmodel.CreateTransactionViewModel
import ru.hse.financialdetective.ui.feature.edittransaction.di.CreateTransactionModule
import ru.hse.financialdetective.ui.feature.edittransaction.di.CreateTransactionScope

@CreateTransactionScope
@Subcomponent(modules = [CreateTransactionModule::class])
interface CreateTransactionComponent {
    fun viewModelFactory(): CreateTransactionViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateTransactionComponent
    }
}
