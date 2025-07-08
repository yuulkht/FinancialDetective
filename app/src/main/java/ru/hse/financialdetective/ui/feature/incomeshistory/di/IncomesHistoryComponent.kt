package ru.hse.financialdetective.ui.feature.incomeshistory.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.expenseshistory.di.IncomesHistoryScope
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesModule
import ru.hse.financialdetective.ui.feature.incomeshistory.viewmodel.IncomesHistoryViewModel

@IncomesHistoryScope
@Subcomponent(modules = [IncomesModule::class])
interface IncomesHistoryComponent {
    fun viewModelFactory(): IncomesHistoryViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): IncomesHistoryComponent
    }
}
