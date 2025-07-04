package ru.hse.financialdetective.ui.uimodel.model

sealed class EditAccountEvent {
    data object SuccessSave : EditAccountEvent()
    data class Error(val message: String) : EditAccountEvent()
}
