package ru.hse.financialdetective.ui.feature.configuretransaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.hse.financialdetective.domain.usecase.CreateTransactionUseCase
import ru.hse.financialdetective.domain.usecase.DeleteTransactionUseCase
import ru.hse.financialdetective.domain.usecase.UpdateTransactionUseCase
import javax.inject.Inject


class ConfigureTransactionViewModel @Inject constructor(
    createTransactionUseCase: CreateTransactionUseCase,
    updateTransactionUseCase: UpdateTransactionUseCase,
    deleteTransactionUseCase: DeleteTransactionUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val createTransactionUseCase: CreateTransactionUseCase,
        private val updateTransactionUseCase: UpdateTransactionUseCase,
        private val deleteTransactionUseCase: DeleteTransactionUseCase

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ConfigureTransactionViewModel(
                createTransactionUseCase,
                updateTransactionUseCase,
                deleteTransactionUseCase
            ) as T
        }
    }
//
//    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading)
//    val uiState: StateFlow<AccountUiState> = _uiState
//
//    private val _event = MutableStateFlow<EditAccountEvent?>(null)
//    val event: StateFlow<EditAccountEvent?> = _event
//
//    val showCurrencyChoiceSheet = mutableStateOf(false)
//
//    init {
//        loadAccountInfo()
//    }
//
//    private fun loadAccountInfo() {
//        viewModelScope.launch {
//            _uiState.value = AccountUiState.Loading
//
//            val result = getAccountInfoUseCase()
//
//            _uiState.value = result.fold(
//                onSuccess = { account ->
//
//                    AccountUiState.Success(account.toUi())
//                },
//                onFailure = { error ->
//                    AccountUiState.Error(error.message ?: DataException.UNRECOGNIZED)
//                }
//            )
//        }
//    }
//
//    fun onTitleChanged(newTitle: String) {
//        val currentState = _uiState.value
//        if (currentState is AccountUiState.Success) {
//            val updatedData = currentState.data.copy(name = newTitle)
//            _uiState.value = AccountUiState.Success(updatedData)
//        }
//    }
//
//    fun onBalanceChanged(newBalance: String) {
//        val currentState = _uiState.value
//        if (currentState is AccountUiState.Success) {
//            val updatedData = currentState.data.copy(balance = newBalance)
//            _uiState.value = AccountUiState.Success(updatedData)
//        }
//    }
//
//    fun onCurrencyChanged(newCurrency: CurrencyUiModel) {
//        val currentState = _uiState.value
//        if (currentState is AccountUiState.Success) {
//            val updatedData = currentState.data.copy(currency = newCurrency)
//            _uiState.value = AccountUiState.Success(updatedData)
//        }
//    }
//
//    fun resetEvent() {
//        _event.value = null
//    }
//
//    fun save() {
//        val currentState = _uiState.value
//        if (currentState is AccountUiState.Success) {
//            val data = currentState.data
//
//            viewModelScope.launch {
//                val result = updateAccountUseCase(
//                    UpdateAccountRequest(
//                        name = data.name,
//                        balance = data.balance,
//                        currency = data.currency.code
//                    )
//                )
//
//                _event.value = result.fold(
//                    onSuccess = {
//                        EditAccountEvent.SuccessSave
//                    },
//                    onFailure = {
//                        EditAccountEvent.Error(
//                            ("Не удалось сохранить изменения. " + it.message)
//                        )
//                    }
//                )
//            }
//        }
//    }
//

}

