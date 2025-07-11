package ru.hse.financialdetective

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.hse.financialdetective.di.AppComponent
import ru.hse.financialdetective.ui.navigation.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val appComponent: AppComponent
        get() = (application as FinancialDetectiveApp).appComponent

    private val accountsComponent by lazy {
        appComponent.accountComponent().create()
    }

    private val expensesComponent by lazy {
        appComponent.expensesComponent().create()
    }

    private val expenseCategoriesComponent by lazy {
        appComponent.categoriesComponent().create()
    }

    private val editAccountComponent by lazy {
        appComponent.editAccountComponent().create()
    }

    private val expensesHistoryComponent by lazy {
        appComponent.expensesHistoryComponent().create()
    }

    private val incomesComponent by lazy {
        appComponent.incomesComponent().create()
    }

    private val incomesHistoryComponent by lazy {
        appComponent.incomesHistoryComponent().create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }
        enableEdgeToEdge()
        setContent {
            MainScreen(
                accountsFactory = accountsComponent.viewModelFactory(),
                expensesFactory = expensesComponent.viewModelFactory(),
                incomesFactory = incomesComponent.viewModelFactory(),
                categoriesFactory = expenseCategoriesComponent.viewModelFactory(),
                incomesHistoryFactory = incomesHistoryComponent.viewModelFactory(),
                expensesHistoryFactory = expensesHistoryComponent.viewModelFactory(),
                editAccountFactory = editAccountComponent.viewModelFactory(),
            )
        }
    }
}