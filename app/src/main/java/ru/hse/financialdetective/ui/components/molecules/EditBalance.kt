package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R

@Composable
fun EditBalance(
    balance: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    ListItem(
        leadIcon = {
            Text(
                text = stringResource(R.string.balance_label), // "Баланс"
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.width(16.dp))
        },
        content = {
            BasicTextField(
                modifier = Modifier,
                value = balance,
                singleLine = true,
                onValueChange = { newValue ->
                    if (newValue.matches(Regex("^\\d*(\\.\\d{0,2})?\$"))) {
                        onTextChanged(newValue)
                    }
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    if (balance.isEmpty()) {
                        Text(
                            text = stringResource(R.string.enter_balance),
                            style = MaterialTheme.typography.bodyLarge,
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
        },
        height = 70.dp,
        showDivider = true,
        modifier = modifier
    )
}


@Preview(apiLevel = 34, showBackground = true)
@Composable
fun EditBalancePreview() {
    Column {
        Spacer(Modifier.height(100.dp))
        val accountTitle = remember { mutableStateOf("1040342.342") }
        EditBalance(
            balance = accountTitle.value,
            onTextChanged = { accountTitle.value = it }
        )
    }

}
