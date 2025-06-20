package ru.hse.financialdetective.data.repository

class ApiException(
    override val message: String
) : Exception(message)