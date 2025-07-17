package ru.hse.financialdetective.ui.feature.expenseanalysis.di

import dagger.Module
import dagger.Provides
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.domain.usecase.CalculateCategoryTransactionsByTransactions
import ru.hse.financialdetective.domain.usecase.GetExpensesForPeriodUseCase
import ru.hse.financialdetective.domain.usecase.GetIncomesForPeriodUseCase
import ru.hse.financialdetective.ui.feature.expenseanalysis.viewmodel.TransactionsAnalysisViewModel

@Module
class TransactionsAnalysisModule {

    @Provides
    @TransactionsAnalysisScope
    fun provideGetExpensesForPeriodUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository
    ): GetExpensesForPeriodUseCase {
        return GetExpensesForPeriodUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @TransactionsAnalysisScope
    fun provideGetIncomesForPeriodUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository
    ): GetIncomesForPeriodUseCase {
        return GetIncomesForPeriodUseCase(accountRepository, transactionRepository)
    }

    @Provides
    @TransactionsAnalysisScope
    fun provideCalculateUseCase(
    ): CalculateCategoryTransactionsByTransactions {
        return CalculateCategoryTransactionsByTransactions()
    }

    @Provides
    @TransactionsAnalysisScope
    fun provideExpensesAnalysisViewModelFactory(
        getExpensesForPeriodUseCase: GetExpensesForPeriodUseCase,
        getIncomesForPeriodUseCase: GetIncomesForPeriodUseCase,
        calculateCategoryTransactionsByTransactions: CalculateCategoryTransactionsByTransactions
    ): TransactionsAnalysisViewModel.Factory {
        return TransactionsAnalysisViewModel.Factory(
            getExpensesForPeriodUseCase,
            getIncomesForPeriodUseCase,
            calculateCategoryTransactionsByTransactions
        )
    }
}
