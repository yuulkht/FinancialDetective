package ru.hse.financialdetective.ui.uimodel.mapper

import ru.hse.financialdetective.domain.model.Account
import ru.hse.financialdetective.ui.uimodel.model.AccountUiModel

fun Account.toUi(): AccountUiModel =
    AccountUiModel(
        id = this.id,
        name = this.name,
        balance = this.balance,
        currency = getCurrencySymbol(this.currency)
    )