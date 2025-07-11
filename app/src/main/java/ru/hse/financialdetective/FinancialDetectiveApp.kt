package ru.hse.financialdetective

import android.app.Application
import ru.hse.financialdetective.di.AppComponent
import ru.hse.financialdetective.di.DaggerAppComponent

class FinancialDetectiveApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}
