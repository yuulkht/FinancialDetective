package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
fun EditComment(
    comment: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    ListItem(
        content = {
            BasicTextField(
                modifier = Modifier,
                value = comment,
                singleLine = true,
                onValueChange = onTextChanged,
                textStyle = MaterialTheme.typography.bodyLarge,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    if (comment.isEmpty()) {
                        Text(
                            text = stringResource(R.string.enter_comment),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
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
fun EditCommentPreview() {
    Column {
        Spacer(Modifier.height(100.dp))
        val accountTitle = remember { mutableStateOf("фурнитура") }
        EditComment(
            comment = accountTitle.value,
            onTextChanged = { accountTitle.value = it }
        )
    }

}
