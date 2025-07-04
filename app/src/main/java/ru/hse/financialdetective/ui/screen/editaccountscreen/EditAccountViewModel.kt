package ru.hse.financialdetective.ui.screen.editaccountscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.model.UpdateAccountRequest
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.domain.usecase.UpdateAccountUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.AccountUiState
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel
import ru.hse.financialdetective.ui.uimodel.model.EditAccountEvent
import javax.inject.Inject

@HiltViewModel
class EditAccountViewModel @Inject constructor(
    private val getAccountInfoUseCase: GetAccountInfoUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading)
    val uiState: StateFlow<AccountUiState> = _uiState

    private val _event = MutableStateFlow<EditAccountEvent?>(null)
    val event: StateFlow<EditAccountEvent?> = _event

    val showCurrencyChoiceSheet = mutableStateOf(false)

    init {
        loadAccountInfo()
    }

    private fun loadAccountInfo() {
        viewModelScope.launch {
            _uiState.value = AccountUiState.Loading

            val result = getAccountInfoUseCase()

            _uiState.value = result.fold(
                onSuccess = { account ->

                    AccountUiState.Success(account.toUi())
                },
                onFailure = { error ->
                    AccountUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }

    fun onTitleChanged(newTitle: String) {
        val currentState = _uiState.value
        if (currentState is AccountUiState.Success) {
            val updatedData = currentState.data.copy(name = newTitle)
            _uiState.value = AccountUiState.Success(updatedData)
        }
    }

    fun onBalanceChanged(newBalance: String) {
        val currentState = _uiState.value
        if (currentState is AccountUiState.Success) {
            val updatedData = currentState.data.copy(balance = newBalance)
            _uiState.value = AccountUiState.Success(updatedData)
        }
    }

    fun onCurrencyChanged(newCurrency: CurrencyUiModel) {
        val currentState = _uiState.value
        if (currentState is AccountUiState.Success) {
            val updatedData = currentState.data.copy(currency = newCurrency)
            _uiState.value = AccountUiState.Success(updatedData)
        }
    }

    fun resetEvent() {
        _event.value = null
    }

    fun save() {
        val currentState = _uiState.value
        if (currentState is AccountUiState.Success) {
            val data = currentState.data

            viewModelScope.launch {
                val result = updateAccountUseCase(
                    UpdateAccountRequest(
                        name = data.name,
                        balance = data.balance,
                        currency = data.currency.code
                    )
                )

                _event.value = result.fold(
                    onSuccess = {
                        EditAccountEvent.SuccessSave
                    },
                    onFailure = {
                        EditAccountEvent.Error(
                            ("Не удалось сохранить изменения. " + it.message)
                        )
                    }
                )
            }
        }
    }


}

