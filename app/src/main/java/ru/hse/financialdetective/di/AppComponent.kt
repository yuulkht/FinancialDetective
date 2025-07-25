package ru.hse.financialdetective.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.hse.financialdetective.data.di.NetworkModule
import ru.hse.financialdetective.data.di.RepositoryModule
import ru.hse.financialdetective.ui.feature.accounts.di.AccountsComponent
import ru.hse.financialdetective.ui.feature.categories.di.ExpenseCategoriesComponent
import ru.hse.financialdetective.ui.feature.createtransaction.di.CreateTransactionComponent
import ru.hse.financialdetective.ui.feature.editaccountscreen.di.EditAccountComponent
import ru.hse.financialdetective.ui.feature.edittransaction.di.EditTransactionComponent
import ru.hse.financialdetective.ui.feature.expenseanalysis.di.TransactionsAnalysisComponent
import ru.hse.financialdetective.ui.feature.expenses.di.ExpensesComponent
import ru.hse.financialdetective.ui.feature.expenseshistory.di.ExpensesHistoryComponent
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesComponent
import ru.hse.financialdetective.ui.feature.incomeshistory.di.IncomesHistoryComponent

@AppScope
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    fun accountComponent(): AccountsComponent.Factory
    fun categoriesComponent(): ExpenseCategoriesComponent.Factory
    fun editAccountComponent(): EditAccountComponent.Factory
    fun expensesComponent(): ExpensesComponent.Factory
    fun expensesHistoryComponent(): ExpensesHistoryComponent.Factory
    fun incomesComponent(): IncomesComponent.Factory
    fun incomesHistoryComponent(): IncomesHistoryComponent.Factory
    fun editTransactionComponent(): EditTransactionComponent.Factory
    fun createTransactionComponent(): CreateTransactionComponent.Factory
    fun transactionsAnalysisComponent(): TransactionsAnalysisComponent.Factory


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
