package ru.hse.financialdetective.ui.uimodel.model

sealed class CreateTransactionEvent {
    data object SuccessSave : CreateTransactionEvent()
    data class Error(val message: String) : CreateTransactionEvent()
}
