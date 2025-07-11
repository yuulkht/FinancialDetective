package ru.hse.financialdetective.domain.repository

import ru.hse.financialdetective.domain.model.Categories

interface CategoryRepository {
    suspend fun getCategories(): Result<Categories>

    suspend fun getCategoriesByType(
        isIncome: Boolean
    ): Result<Categories>
}