package ru.hse.financialdetective.ui.feature.categories.di

import dagger.Subcomponent
import ru.hse.financialdetective.ui.feature.categories.viewmodel.ExpenseCategoriesViewModel

@ExpenseCategoriesScope
@Subcomponent(modules = [ExpenseCategoriesModule::class])
interface ExpenseCategoriesComponent {
    fun viewModelFactory(): ExpenseCategoriesViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExpenseCategoriesComponent
    }
}
