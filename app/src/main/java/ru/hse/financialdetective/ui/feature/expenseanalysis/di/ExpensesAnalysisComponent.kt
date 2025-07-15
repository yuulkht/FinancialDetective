package ru.hse.financialdetective.ui.feature.expenseanalysis.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel.ExpenseAnalysisViewModel

@ExpensesAnalysisScope
@Subcomponent(modules = [ExpensesAnalysisModule::class])
interface ExpensesAnalysisComponent {
    fun viewModelFactory(): ExpenseAnalysisViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExpensesAnalysisComponent
    }
}
