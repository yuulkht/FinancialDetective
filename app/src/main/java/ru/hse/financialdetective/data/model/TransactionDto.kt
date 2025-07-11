package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class TransactionDtoRq(
    @JsonProperty("accountId")
    val account: AccountBrief,
    @JsonProperty("categoryId")
    val categoryDto: CategoryDto,
    @JsonProperty("amount")
    val amount: String,
    @JsonProperty("transactionDate")
    val transactionDate: Instant,
    @JsonProperty("comment")
    val comment: String?
)

data class TransactionDtoRs(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("accountId")
    val account: AccountBrief,
    @JsonProperty("categoryId")
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

