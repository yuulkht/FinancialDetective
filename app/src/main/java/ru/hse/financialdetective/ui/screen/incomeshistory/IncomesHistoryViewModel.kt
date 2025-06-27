package ru.hse.financialdetective.ui.screen.incomeshistory

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.hse.financialdetective.data.exception.DataException
import ru.hse.financialdetective.domain.usecase.GetIncomesForPeriodUseCase
import ru.hse.financialdetective.ui.uimodel.mapper.toUi
import ru.hse.financialdetective.ui.uimodel.model.IncomesUiState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class IncomesHistoryViewModel @Inject constructor(
    private val getIncomesForPeriodUseCase: GetIncomesForPeriodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<IncomesUiState>(IncomesUiState.Loading)
    val uiState: StateFlow<IncomesUiState> = _uiState

    val dateFrom = mutableStateOf(LocalDate.now().withDayOfMonth(1))
    val dateTo = mutableStateOf(LocalDate.now())

    fun loadForPeriodIncomes() {
        viewModelScope.launch {
            _uiState.value = IncomesUiState.Loading

            val result = getIncomesForPeriodUseCase(
                dateFrom.value.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                dateTo.value.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()
            )
            _uiState.value = result.fold(
                onSuccess = { expenses ->
                    IncomesUiState.Success(expenses.toUi())
                },
                onFailure = { error ->
                    IncomesUiState.Error(error.message ?: DataException.UNRECOGNIZED) //TODO
                }
            )
        }
    }
}

