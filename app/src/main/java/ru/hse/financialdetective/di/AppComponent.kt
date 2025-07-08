package ru.hse.financialdetective.di

import dagger.Component
import ru.hse.financialdetective.data.di.DataComponent
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository
import ru.hse.financialdetective.ui.feature.accounts.di.AccountsComponent
import ru.hse.financialdetective.ui.feature.categories.di.ExpenseCategoriesComponent
import ru.hse.financialdetective.ui.feature.editaccountscreen.di.EditAccountComponent
import ru.hse.financialdetective.ui.feature.expenses.di.ExpensesComponent
import ru.hse.financialdetective.ui.feature.expenseshistory.di.ExpensesHistoryComponent
import ru.hse.financialdetective.ui.feature.incomes.di.IncomesComponent
import ru.hse.financialdetective.ui.feature.incomeshistory.di.IncomesHistoryComponent

@AppScope
@Component
interface AppComponent {

    fun dataComponent(): DataComponent.Factory
    fun accountComponent(): AccountsComponent
    fun categoriesComponent(): ExpenseCategoriesComponent
    fun editAccountComponent(): EditAccountComponent
    fun expensesComponent(): ExpensesComponent
    fun expensesHistoryComponent(): ExpensesHistoryComponent
    fun IncomesComponent(): IncomesComponent
    fun IncomesHistoryComponent(): IncomesHistoryComponent


    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
