package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AccountDto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("userId")
    val userId: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("balance")
    val balance: String,
    @JsonProperty("currency")
    val currency: String,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant
)
