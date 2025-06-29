package ru.hse.financialdetective.domain.usecase

import jakarta.inject.Inject
import ru.hse.financialdetective.domain.model.Categories

/**
 * Отвечает за фильтрацию списка категорий по переданному значению (поиск по названию)
 */
class FilterCategoriesUseCase @Inject constructor() {
    operator fun invoke(
        categories: Categories,
        searchValue: String
    ): Categories {
        if (searchValue.isBlank()) return categories

        val filtered = categories.items.filter { category ->
            category.name.contains(searchValue, ignoreCase = true)
        }

        return Categories(items = filtered)
    }
}
