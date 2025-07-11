package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class TransactionDtoRq(
    @JsonProperty("accountId")
    val accountId: Int,
    @JsonProperty("categoryId")
    val categoryId: Int,
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
    val accountId: Int,
    @JsonProperty("categoryId")
    val categoryId: Int,
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

