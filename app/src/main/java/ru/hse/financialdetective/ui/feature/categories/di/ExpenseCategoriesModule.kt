package ru.hse.financialdetective.ui.feature.categories.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.CategoryRepository
import ru.hse.financialdetective.domain.usecase.FilterCategoriesUseCase
import ru.hse.financialdetective.domain.usecase.GetCategoriesUseCase
import ru.hse.financialdetective.ui.feature.categories.viewmodel.ExpenseCategoriesViewModel

@Module
class ExpenseCategoriesModule {

    @Provides
    @ExpenseCategoriesScope
    fun provideGetCategoriesUseCase(categoryRepository: CategoryRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(categoryRepository)
    }

    @Provides
    @ExpenseCategoriesScope
    fun provideFilterCategoriesUseCase(): FilterCategoriesUseCase {
        return FilterCategoriesUseCase()
    }

    @Provides
    @ExpenseCategoriesScope
    fun provideExpensesCategoriesViewModelFactory(
        getCategoriesUseCase: GetCategoriesUseCase,
        filterCategoriesUseCase: FilterCategoriesUseCase
    ): ExpenseCategoriesViewModel.Factory {
        return ExpenseCategoriesViewModel.Factory(getCategoriesUseCase, filterCategoriesUseCase)
    }
}
