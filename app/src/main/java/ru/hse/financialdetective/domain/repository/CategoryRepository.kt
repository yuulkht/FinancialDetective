package ru.hse.financialdetective.domain.repository

import ru.hse.financialdetective.domain.model.Categories

interface CategoryRepository {
    suspend fun getCategories(): Result<Categories>
}