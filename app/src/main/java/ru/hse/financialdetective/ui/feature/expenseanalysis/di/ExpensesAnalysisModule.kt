package ru.hse.financialdetective.ui.feature.expenseanalysis.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.CalculateCategoryExpensesByExpenses
import ru.hse.financialdetective.domain.usecase.GetExpensesForPeriodUseCase
import ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel.ExpenseAnalysisViewModel

@Module
class ExpensesAnalysisModule {

    @Provides
    @ExpensesAnalysisScope
    fun provideGetExpensesForPeriodUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository
    ): GetExpensesForPeriodUseCase {
        return GetExpensesForPeriodUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @ExpensesAnalysisScope
    fun provideCalculateUseCase(
    ): CalculateCategoryExpensesByExpenses {
        return CalculateCategoryExpensesByExpenses()
    }

    @Provides
    @ExpensesAnalysisScope
    fun provideExpensesAnalysisViewModelFactory(
        getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase,
        calculateCategoryExpensesByExpenses: CalculateCategoryExpensesByExpenses
    ): ExpenseAnalysisViewModel.Factory {
        return ExpenseAnalysisViewModel.Factory(
            getExpensesForPeriodUseCase,
            calculateCategoryExpensesByExpenses
        )
    }
}
