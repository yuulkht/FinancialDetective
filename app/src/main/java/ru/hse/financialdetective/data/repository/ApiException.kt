package ru.hse.financialdetective.data.repository

class ApiException(
    override val message: String
) : Exception(message) {
    companion object {
        const val UNAUTHORIZED = "Неавторизованный доступ"
        const val UNRECOGNIZED = "Неизвестная ошибка"
        const val SERVER_ERROR = "Ошибка сервера или сети"

        const val NO_ACCOUNTS = "Список аккаунтов пуст"
        const val NO_TRANSACTIONS = "Не удалось получить список транзакций"
        const val INCORRECT_FORMAT = "Неверный формат переданных параметров"
    }
}