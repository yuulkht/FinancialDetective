package ru.hse.financialdetective.di

import dagger.Component
import ru.hse.financialdetective.data.di.DataComponent
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.repository.AccountRepository
import ru.hse.financialdetective.domain.repository.TransactionRepository

@AppScope
@Component
interface AppComponent {
    fun dataComponent(): DataComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
