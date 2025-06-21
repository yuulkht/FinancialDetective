package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class StateItem(
    @JsonProperty("categoryId")
    val categoryId: Int,
    @JsonProperty("categoryName")
    val categoryName: String,
    @JsonProperty("emoji")
    val emoji: String,
    @JsonProperty("amount")
    val amount: String
)