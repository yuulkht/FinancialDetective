package ru.hse.financialdetective.ui.screen.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetCategoriesUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.CategoriesUiState
import javax.inject.Inject

@HiltViewModel
class ExpenseCategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val uiState: StateFlow<CategoriesUiState> = _uiState

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = CategoriesUiState.Loading

            val result = getCategoriesUseCase()

            _uiState.value = result.fold(
                onSuccess = { categories ->
                    CategoriesUiState.Success(categories.toUi())
                },
                onFailure = { error ->
                    CategoriesUiState.Error(error.message ?: DataException.UNRECOGNIZED)
                }
            )
        }
    }
}

