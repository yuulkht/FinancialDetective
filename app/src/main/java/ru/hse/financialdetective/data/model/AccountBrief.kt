package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AccountBrief(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("balance")
    val balance: String,
    @JsonProperty("currency")
    val currency: String
)