package ru.hse.financialdetective.ui.feature.expenseanalysis.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel.TransactionsAnalysisViewModel

@TransactionsAnalysisScope
@Subcomponent(modules = [TransactionsAnalysisModule::class])
interface TransactionsAnalysisComponent {
    fun viewModelFactory(): TransactionsAnalysisViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): TransactionsAnalysisComponent
    }
}
