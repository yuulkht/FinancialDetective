package ru.hse.financialdetective.data.repository

import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.data.model.CategoriesResponse
import ru.hse.financialdetective.data.network.ApiService
import ru.hse.financialdetective.domain.mapper.todomain.toDomain
import ru.hse.financialdetective.domain.model.Categories
import ru.hse.financialdetective.domain.repository.CategoryRepository
import javax.inject.Inject

/**
 * Отвечает за вызов методов АПИ-сервиса, связанных с категориями и обработку ответа
 */
class CategoryRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CategoryRepository {
    override suspend fun getCategories(): Result<Categories> {
        return try {
            val response = api.getCategories()

            when (response.code()) {
                200 -> {
                    val categories = response.body() ?: return Result.failure(
                        DataException(
                            DataException.NO_CATEGORIES
                        )
                    )
                    Result.success(CategoriesResponse(categories).toDomain())
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }

    override suspend fun getIncomeCategories(): Result<Categories> {
        return try {
            val response = api.getCategoriesByType(true)

            when (response.code()) {
                200 -> {
                    val categories = response.body() ?: return Result.failure(
                        DataException(
                            DataException.NO_CATEGORIES
                        )
                    )
                    Result.success(CategoriesResponse(categories).toDomain())
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }

    override suspend fun getExpenseCategories(): Result<Categories> {
        return try {
            val response = api.getCategoriesByType(false)

            when (response.code()) {
                200 -> {
                    val categories = response.body() ?: return Result.failure(
                        DataException(
                            DataException.NO_CATEGORIES
                        )
                    )
                    Result.success(CategoriesResponse(categories).toDomain())
                }

                401 -> {
                    Result.failure(DataException(DataException.UNAUTHORIZED))
                }

                else -> {
                    Result.failure(DataException("${DataException.UNRECOGNIZED}: ${response.code()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(DataException(DataException.SERVER_ERROR))
        }
    }
}
