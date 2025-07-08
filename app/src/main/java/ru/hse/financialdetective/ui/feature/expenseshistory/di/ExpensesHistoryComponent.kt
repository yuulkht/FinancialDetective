package ru.hse.financialdetective.ui.feature.expenseshistory.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.expenseshistory.viewmodel.ExpensesHistoryViewModel
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesModule
import ru.hse.financialdetective.ui.feature.incomeshistory.di.IncomesHistoryComponent

@ExpensesHistoryScope
@Subcomponent(modules = [ExpensesHistoryModule::class])
interface ExpensesHistoryComponent {
    fun viewModelFactory(): ExpensesHistoryViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExpensesHistoryComponent
    }
}
