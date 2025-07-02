package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.ui.components.molecules.listitems.CancelBottomSheetItem
import ru.hse.financialdetective.ui.components.molecules.listitems.CurrencyBottomSheetItem
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyChangeBottomSheet(
    selectedCurrency: MutableState<CurrencyUiModel>,
    showSheet: MutableState<Boolean>
) {
    ModalBottomSheet(
        onDismissRequest = { showSheet.value = false },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CurrencyBottomSheetItem(
                    currency = CurrencyUiModel.RUB,
                    onClick = {
                        selectedCurrency.value = CurrencyUiModel.RUB
                    }
                )
                CurrencyBottomSheetItem(
                    currency = CurrencyUiModel.USD,
                    onClick = {
                        selectedCurrency.value = CurrencyUiModel.USD
                    }
                )
                CurrencyBottomSheetItem(
                    currency = CurrencyUiModel.EUR,
                    onClick = {
                        selectedCurrency.value = CurrencyUiModel.EUR
                    }
                )
                CancelBottomSheetItem(
                    onClick = {
                        showSheet.value = false
                    }
                )
            }
        }
    }
}

@Composable
@Preview(apiLevel = 34, showBackground = true)
fun CurrencyChangeBottomSheetPreview() {
    CurrencyChangeBottomSheet(
        selectedCurrency = remember { mutableStateOf(CurrencyUiModel.RUB) },
        showSheet = remember { mutableStateOf(true) }
    )
}