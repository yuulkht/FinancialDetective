package ru.hse.financialdetective.ui.feature.createtransaction.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.model.TransactionRequest
import ru.hse.financialdetective.domain.usecase.CreateTransactionUseCase
import ru.hse.financialdetective.domain.usecase.GetAccountInfoUseCase
import ru.hse.financialdetective.domain.usecase.GetCategoriesByTypeUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.formatLocalDate
import ru.hse.financialdetective.ui.uimodel.mapper.formatLocalTime
import ru.hse.financialdetective.ui.uimodel.mapper.stringsToInstant
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel
import ru.hse.financialdetective.ui.uimodel.model.CreateTransactionEvent
import ru.hse.financialdetective.ui.uimodel.model.CreateTransactionUiModel
import ru.hse.financialdetective.ui.uimodel.model.CreateTransactionUiState
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject


class CreateTransactionViewModel @Inject constructor(
    private val getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val getAccountInfoUseCase: GetAccountInfoUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
        private val createTransactionUseCase: CreateTransactionUseCase,
        private val getAccountInfoUseCase: GetAccountInfoUseCase

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CreateTransactionViewModel(
                getCategoriesByTypeUseCase,
                createTransactionUseCase,
                getAccountInfoUseCase
            ) as T
        }
    }

    private val _uiState =
        MutableStateFlow<CreateTransactionUiState>(CreateTransactionUiState.Loading)
    val uiState: StateFlow<CreateTransactionUiState> = _uiState

    private val _event = MutableStateFlow<CreateTransactionEvent?>(null)
    val event: StateFlow<CreateTransactionEvent?> = _event

    val isIncome = mutableStateOf(false)

    fun loadInfo(isIncomeTransaction: Boolean) {
        isIncome.value = isIncomeTransaction
        viewModelScope.launch {
            _uiState.value = CreateTransactionUiState.Loading

            val result = getAccountInfoUseCase()

            _uiState.value = result.fold(
                onSuccess = { account ->
                    val categoriesResult = getCategoriesByTypeUseCase(isIncome.value)

                    categoriesResult.fold(
                        onSuccess = { categories ->
                            CreateTransactionUiState.Success(
                                CreateTransactionUiModel(
                                    account = account.toUi(),
                                    category = null,
                                    amount = "",
                                    date = formatLocalDate(LocalDate.now()),
                                    time = formatLocalTime(LocalTime.now()),
                                    comment = "",
                                    categories = categories.toUi().categories
                                )
                            )
                        },
                        onFailure = { error ->
                            CreateTransactionUiState.Error(
                                error.message ?: DataException.UNRECOGNIZED
                            )
                        }
                    )
                },
                onFailure = { error ->
                    CreateTransactionUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }

    fun onCategoryChanged(categoryUiModel: CategoryUiModel) {
        val currentState = _uiState.value
        if (currentState is CreateTransactionUiState.Success) {
            val updatedData = currentState.data.copy(category = categoryUiModel)
            _uiState.value = CreateTransactionUiState.Success(updatedData)
        }
    }

    fun onAmountChanged(amount: String) {
        val currentState = _uiState.value
        if (currentState is CreateTransactionUiState.Success) {
            val updatedData = currentState.data.copy(amount = amount)
            _uiState.value = CreateTransactionUiState.Success(updatedData)
        }
    }

    fun onDateChanged(date: LocalDate?) {
        val currentState = _uiState.value
        if (currentState is CreateTransactionUiState.Success) {
            val updatedData =
                currentState.data.copy(date = formatLocalDate(date ?: LocalDate.now()))
            _uiState.value = CreateTransactionUiState.Success(updatedData)
        }
    }

    fun onTimeChanged(time: LocalTime?) {
        val currentState = _uiState.value
        if (currentState is CreateTransactionUiState.Success) {
            val updatedData =
                currentState.data.copy(time = formatLocalTime(time ?: LocalTime.now()))
            _uiState.value = CreateTransactionUiState.Success(updatedData)
        }
    }

    fun onCommentChanged(comment: String) {
        val currentState = _uiState.value
        if (currentState is CreateTransactionUiState.Success) {
            val updatedData = currentState.data.copy(comment = comment)
            _uiState.value = CreateTransactionUiState.Success(updatedData)
        }
    }


    fun resetEvent() {
        _event.value = null
    }

    fun save() {
        val currentState = _uiState.value
        if (currentState is CreateTransactionUiState.Success) {
            val data = currentState.data

            viewModelScope.launch {
                if (data.category == null || data.amount == "") {
                    _event.value = CreateTransactionEvent.Error(
                        ("Вы заполнили не все поля")
                    )
                } else {
                    val result = createTransactionUseCase(
                        transactionRequest = TransactionRequest(
                            categoryId = data.category.id,
                            amount = data.amount,
                            transactionDate = stringsToInstant(data.date, data.time),
                            comment = data.comment
                        )
                    )

                    _event.value = result.fold(
                        onSuccess = {
                            CreateTransactionEvent.SuccessSave
                        },
                        onFailure = {
                            CreateTransactionEvent.Error(
                                ("Не удалось сохранить изменения. " + it.message)
                            )
                        }
                    )
                }
            }
        }
    }

}

