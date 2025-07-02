package ru.hse.financialdetective.ui.screen.editaccountscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.AccountUiState
import javax.inject.Inject

@HiltViewModel
class EditAccountViewModel @Inject constructor(
    private val getAccountInfoUseCase: GetAccountInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading)
    val uiState: StateFlow<AccountUiState> = _uiState

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
}

