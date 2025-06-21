package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class TransactionResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("account")
    val account: AccountBrief,
    @JsonProperty("category")
    val categoryDto: CategoryDto,
    @JsonProperty("amount")
    val amount: String,
    @JsonProperty("transactionDate")
    val transactionDate: Instant,
    @JsonProperty("comment")
    val comment: String?,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant
)

data class TransactionsResponse(
    val transactions: List<TransactionResponse>
)