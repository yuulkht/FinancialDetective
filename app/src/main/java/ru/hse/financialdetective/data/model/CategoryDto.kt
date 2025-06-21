package ru.hse.financialdetective.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryDto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("emoji")
    val emoji: String,
    @JsonProperty("isIncome")
    val isIncome: Boolean
)