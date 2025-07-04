package ru.hse.financialdetective.ui.components.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hse.financialdetective.ui.components.molecules.listitems.CancelBottomSheetItem
import ru.hse.financialdetective.ui.components.molecules.listitems.CurrencyBottomSheetItem
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyChangeBottomSheet(
    currentCurrency: CurrencyUiModel,
    onCurrencySelected: (CurrencyUiModel) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CurrencyBottomSheetItem(
                currency = CurrencyUiModel.RUB,
                onClick = {
                    onCurrencySelected(CurrencyUiModel.RUB)
                    onDismissRequest()
                }
            )
            CurrencyBottomSheetItem(
                currency = CurrencyUiModel.USD,
                onClick = {
                    onCurrencySelected(CurrencyUiModel.USD)
                    onDismissRequest()
                }
            )
            CurrencyBottomSheetItem(
                currency = CurrencyUiModel.EUR,
                onClick = {
                    onCurrencySelected(CurrencyUiModel.EUR)
                    onDismissRequest()
                }
            )
            CancelBottomSheetItem(
                onClick = onDismissRequest
            )
        }
    }
}


@Composable
@Preview(apiLevel = 34, showBackground = true)
fun CurrencyChangeBottomSheetPreview() {
    CurrencyChangeBottomSheet(
        currentCurrency = CurrencyUiModel.RUB,
        onCurrencySelected = {},
        onDismissRequest = {}
    )
}