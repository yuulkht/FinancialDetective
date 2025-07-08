package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateAccountDto(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("balance")
    val balance: String,
    @JsonProperty("currency")
    val currency: String
)