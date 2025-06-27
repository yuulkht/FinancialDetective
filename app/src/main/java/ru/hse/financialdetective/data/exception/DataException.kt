package ru.hse.financialdetective.data.exception

/**
 * Отвечает за ошибки при загрузке данных
 */
class DataException(
    override val message: String
) : Exception(message) {
    companion object {
        const val UNAUTHORIZED = "Неавторизованный доступ"
        const val UNRECOGNIZED = "Неизвестная ошибка"
        const val SERVER_ERROR = "Ошибка сервера или сети"

        const val NO_ACCOUNTS = "Список аккаунтов пуст"
        const val NO_TRANSACTIONS = "Не удалось получить список транзакций"

        const val INCORRECT_FORMAT = "Неверный формат переданных параметров"

        const val FAIL_TO_GET_ID = "Не удалось получить ID счета"
    }
}