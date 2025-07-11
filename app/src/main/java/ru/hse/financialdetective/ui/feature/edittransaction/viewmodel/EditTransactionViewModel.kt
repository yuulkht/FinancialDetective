package ru.hse.financialdetective.ui.feature.edittransaction.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.model.TransactionRequest
import ru.hse.financialdetective.domain.usecase.DeleteTransactionUseCase
import ru.hse.financialdetective.domain.usecase.GetCategoriesByTypeUseCase
import ru.hse.financialdetective.domain.usecase.GetTransactionByIdUseCase
import ru.hse.financialdetective.domain.usecase.UpdateTransactionUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.formatLocalDate
import ru.hse.financialdetective.ui.uimodel.mapper.formatLocalTime
import ru.hse.financialdetective.ui.uimodel.mapper.stringsToInstant
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel
import ru.hse.financialdetective.ui.uimodel.model.EditTransactionEvent
import ru.hse.financialdetective.ui.uimodel.model.TransactionUiState
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject


class EditTransactionViewModel @Inject constructor(
    private val getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
        private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
        private val updateTransactionUseCase: UpdateTransactionUseCase,
        private val deleteTransactionUseCase: DeleteTransactionUseCase

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditTransactionViewModel(
                getCategoriesByTypeUseCase,
                getTransactionByIdUseCase,
                updateTransactionUseCase,
                deleteTransactionUseCase
            ) as T
        }
    }

    private val _uiState = MutableStateFlow<TransactionUiState>(TransactionUiState.Loading)
    val uiState: StateFlow<TransactionUiState> = _uiState

    private val _event = MutableStateFlow<EditTransactionEvent?>(null)
    val event: StateFlow<EditTransactionEvent?> = _event

    val isIncome = mutableStateOf(false)

    fun loadInfo(id: Int) {
        viewModelScope.launch {
            _uiState.value = TransactionUiState.Loading

            val result = getTransactionByIdUseCase(id)

            _uiState.value = result.fold(
                onSuccess = { transaction ->
                    isIncome.value = transaction.category.isIncome

                    val categoriesResult = getCategoriesByTypeUseCase(isIncome.value)

                    categoriesResult.fold(
                        onSuccess = { categories ->
                            TransactionUiState.Success(transaction.toUi(categories.toUi().categories))
                        },
                        onFailure = { error ->
                            TransactionUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                        }
                    )
                },
                onFailure = { error ->
                    TransactionUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }

    fun onCategoryChanged(categoryUiModel: CategoryUiModel) {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val updatedData = currentState.data.copy(category = categoryUiModel)
            _uiState.value = TransactionUiState.Success(updatedData)
        }
    }

    fun onAmountChanged(amount: String) {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val updatedData = currentState.data.copy(amount = amount)
            _uiState.value = TransactionUiState.Success(updatedData)
        }
    }

    fun onDateChanged(date: LocalDate?) {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val updatedData =
                currentState.data.copy(date = formatLocalDate(date ?: LocalDate.now()))
            _uiState.value = TransactionUiState.Success(updatedData)
        }
    }

    fun onTimeChanged(time: LocalTime?) {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val updatedData =
                currentState.data.copy(time = formatLocalTime(time ?: LocalTime.now()))
            _uiState.value = TransactionUiState.Success(updatedData)
        }
    }

    fun onCommentChanged(comment: String) {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val updatedData = currentState.data.copy(comment = comment)
            _uiState.value = TransactionUiState.Success(updatedData)
        }
    }


    fun resetEvent() {
        _event.value = null
    }

    fun save() {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val data = currentState.data

            viewModelScope.launch {
                if (data.amount == "") {
                    _event.value = EditTransactionEvent.Error(
                        ("Вы заполнили не все поля")
                    )
                } else {
                    val result = updateTransactionUseCase(
                        transactionId = data.id,
                        transactionRequest = TransactionRequest(
                            categoryId = data.category.id,
                            amount = data.amount,
                            transactionDate = stringsToInstant(data.date, data.time),
                            comment = data.comment
                        )
                    )

                    _event.value = result.fold(
                        onSuccess = {
                            EditTransactionEvent.SuccessSave
                        },
                        onFailure = {
                            EditTransactionEvent.Error(
                                ("Не удалось сохранить изменения. " + it.message)
                            )
                        }
                    )
                }
            }
        }
    }

    fun deleteTransaction() {
        val currentState = _uiState.value
        if (currentState is TransactionUiState.Success) {
            val data = currentState.data

            viewModelScope.launch {
                val result = deleteTransactionUseCase(
                    transactionId = data.id,
                )

                _event.value = result.fold(
                    onSuccess = {
                        EditTransactionEvent.SuccessSave
                    },
                    onFailure = {
                        EditTransactionEvent.Error(
                            ("Не удалось сохранить изменения. " + it.message)
                        )
                    }
                )
            }
        }
    }

}

