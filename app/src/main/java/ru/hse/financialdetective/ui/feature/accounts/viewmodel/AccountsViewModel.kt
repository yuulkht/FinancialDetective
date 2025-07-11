package ru.hse.financialdetective.ui.feature.accounts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.AccountUiState
import javax.inject.Inject

class AccountsViewModel @Inject constructor(
    private val getAccountInfoUseCase: GetAccountInfoUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getAccountInfoUseCase: GetAccountInfoUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AccountsViewModel(getAccountInfoUseCase) as T
        }
    }

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading)
    val uiState: StateFlow<AccountUiState> = _uiState

    init {
        loadAccountInfo()
    }

    fun loadAccountInfo() {
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
}

