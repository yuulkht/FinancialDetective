package ru.hse.financialdetective.ui.feature.expenses.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.expenses.viewmodel.ExpensesViewModel
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesComponent
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesModule

@ExpensesScope
@Subcomponent(modules = [ExpensesModule::class])
interface ExpensesComponent {
    fun viewModelFactory(): ExpensesViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExpensesComponent
    }
}
