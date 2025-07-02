package ru.hse.financialdetective.ui.screen.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.hse.financialdetective.ui.components.error.ErrorScreen
import ru.hse.financialdetective.ui.components.loading.LoadingScreen
import ru.hse.financialdetective.ui.components.molecules.common.SearchBar
import ru.hse.financialdetective.ui.components.organisms.CategoriesList
import ru.hse.financialdetective.ui.components.organisms.ScreenHeader
import ru.hse.financialdetective.ui.theme.GreenBright
import ru.hse.financialdetective.ui.uimodel.model.CategoriesUiState

@Composable
fun ExpenseCategoriesScreen(
    navController: NavController,
    viewModel: ExpenseCategoriesViewModel = hiltViewModel()
) {
    //TODO подумать где лучше хранить логику и search
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    when (uiState) {
        is CategoriesUiState.Loading -> {
            LoadingScreen()
        }

        is CategoriesUiState.Error -> {
            ErrorScreen()
        }

        is CategoriesUiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ScreenHeader(title = "Мои статьи", color = GreenBright)
                SearchBar(
                    text = searchQuery.value,
                    onTextChange = { searchQuery.value = it },
                    onSearchClick = {
                        viewModel.filterCategoriesByName(searchQuery.value)
                    }
                )
                CategoriesList(categories = (uiState as CategoriesUiState.Success).data.categories)
            }
        }
    }
}