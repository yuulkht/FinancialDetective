package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.TransactionDetailed
import ru.hse.financialdetective.ui.uimodel.model.CategoryUiModel
import ru.hse.financialdetective.ui.uimodel.model.TransactionUiModel

fun TransactionDetailed.toUi(
    categories: List<CategoryUiModel>
): TransactionUiModel {

    return TransactionUiModel(
        id = this.id,
        account = this.account.toUi(),
        category = this.category.toUi(),
        amount = this.amount.toString(),
        date = getDateFromInstant(this.date),
        time = getTimeFromInstant(this.date),
        comment = this.comment,
        categories = categories
    )
}
