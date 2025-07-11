package ru.hse.financialdetective.ui.feature.categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.model.Categories
import ru.hse.financialdetective.domain.usecase.FilterCategoriesUseCase
import ru.hse.financialdetective.domain.usecase.GetCategoriesUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.CategoriesUiState
import javax.inject.Inject

class ExpenseCategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val filterCategoriesUseCase: FilterCategoriesUseCase
) : ViewModel() {

    class Factory @Inject constructor(
        private val getCategoriesUseCase: GetCategoriesUseCase,
        private val filterCategoriesUseCase: FilterCategoriesUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExpenseCategoriesViewModel(getCategoriesUseCase, filterCategoriesUseCase) as T
        }
    }

    private val _uiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val uiState: StateFlow<CategoriesUiState> = _uiState

    private var originalCategories: Categories = Categories(emptyList())

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = CategoriesUiState.Loading

            val result = getCategoriesUseCase()

            _uiState.value = result.fold(
                onSuccess = { categories ->
                    originalCategories = categories
                    CategoriesUiState.Success(categories.toUi())
                },
                onFailure = { error ->
                    CategoriesUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }

    fun filterCategoriesByName(searchValue: String) {
        if (_uiState.value is CategoriesUiState.Success) {
            val filtered = filterCategoriesUseCase(
                categories = originalCategories,
                searchValue = searchValue
            )

            _uiState.value = CategoriesUiState.Success(filtered.toUi())
        }
    }
}

