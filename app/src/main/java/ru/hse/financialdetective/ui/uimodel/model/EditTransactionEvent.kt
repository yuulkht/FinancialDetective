package ru.hse.financialdetective.ui.uimodel.model

sealed class EditTransactionEvent {
    data object SuccessSave : EditTransactionEvent()
    data class Error(val message: String) : EditTransactionEvent()
}
