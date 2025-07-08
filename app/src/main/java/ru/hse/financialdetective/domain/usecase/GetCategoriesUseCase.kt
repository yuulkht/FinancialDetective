package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.data.repository.CategoryRepository
import ru.hse.financialdetective.domain.model.Categories

/**
 * Отвечает за получение информации о категориях пользователя
 */
class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(): Result<Categories> {
        val categoryResponse = categoryRepository.getCategories()

        return categoryResponse
    }
}
