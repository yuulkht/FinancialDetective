package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AccountResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("balance")
    val balance: String,
    @JsonProperty("currency")
    val currency: String,
    @JsonProperty("incomeStats")
    val incomeStats: List<StateItem>,
    @JsonProperty("expenseStats")
    val expenseStats: List<StateItem>,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant
)