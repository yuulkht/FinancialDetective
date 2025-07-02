package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreyDark

@Composable
fun EditAccountName(
    accountTitle: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    ListItem(
        leadIcon = {
            Icon(
                painter = painterResource(R.drawable.person),
                contentDescription = stringResource(R.string.person),
                modifier = Modifier
                    .size(24.dp),
                tint = GreyDark
            )
            Spacer(Modifier.width(16.dp))
        },
        content = {
            BasicTextField(
                modifier = Modifier,
                value = accountTitle,
                singleLine = true,
                onValueChange = onTextChanged,
                textStyle = MaterialTheme.typography.bodyLarge,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    if (accountTitle.isEmpty()) {
                        Text(
                            text = stringResource(R.string.enter_account_name),
                        )
                    }

                    innerTextField()
                },
                keyboardOptions = KeyboardOptions(
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
fun EditAccountNamePreview() {
    Column {
        Spacer(Modifier.height(100.dp))
        val accountTitle = remember { mutableStateOf("Зарплата") }
        EditAccountName(
            accountTitle = accountTitle.value,
            onTextChanged = { accountTitle.value = it }
        )
    }

}
