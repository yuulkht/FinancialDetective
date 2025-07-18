package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.domain.model.Currency
import ru.hse.financialdetective.ui.uimodel.mapper.toUiModel
import ru.hse.financialdetective.ui.uimodel.model.CurrencyUiModel

@Composable
fun EditTransactionSum(
    sum: String,
    onSumChanged: (String) -> Unit,
    currency: CurrencyUiModel,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    ListItem(
        leadIcon = {
            Text(
                text = stringResource(R.string.sum),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.width(16.dp))
        },
        content = {},
        tailIcon = {
            Row {
                BasicTextField(
                    modifier = Modifier.weight(1f),
                    value = sum,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.matches(Regex("^\\d*(\\.\\d{0,2})?\$"))) {
                            onSumChanged(newValue)
                        }
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        textAlign = TextAlign.End
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                    decorationBox = { innerTextField ->
                        if (sum.isEmpty()) {
                            Text(
                                text = stringResource(R.string.enter_sum),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    textAlign = TextAlign.End
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        innerTextField()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = currency.symbol,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        height = 70.dp,
        showDivider = true,
        modifier = modifier
    )
}


@Preview(apiLevel = 34, showBackground = true)
@Composable
fun EditTransactionSumPreview() {
    Column {
        Spacer(Modifier.height(100.dp))
        val accountTitle = remember { mutableStateOf("1040342.34") }
        EditTransactionSum(
            sum = accountTitle.value,
            onSumChanged = { accountTitle.value = it },
            currency = Currency.RUB.toUiModel()
        )
    }

}
