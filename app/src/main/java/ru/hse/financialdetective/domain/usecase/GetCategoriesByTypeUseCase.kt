package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.domain.model.Categories
import ru.hse.financialdetective.domain.repository.CategoryRepository

/**
 * Отвечает за получение информации о категориях пользователя
 */
class GetCategoriesByTypeUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(
        isIncome: Boolean
    ): Result<Categories> {
        val categoryResponse = categoryRepository.getCategoriesByType(isIncome)

        return categoryResponse
    }
}
