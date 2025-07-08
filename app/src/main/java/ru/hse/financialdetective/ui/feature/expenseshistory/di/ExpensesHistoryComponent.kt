package ru.hse.financialdetective.ui.feature.expenseshistory.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.expenseshistory.viewmodel.ExpensesHistoryViewModel
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesModule
import ru.hse.financialdetective.ui.feature.incomeshistory.di.IncomesHistoryComponent

@IncomesHistoryScope
@Subcomponent(modules = [IncomesModule::class])
interface ExpensesHistoryComponent {
    fun viewModelFactory(): ExpensesHistoryViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): IncomesHistoryComponent
    }
}
