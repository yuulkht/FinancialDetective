package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R

@Composable
fun EditTransactionAccount(
    account: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        leadIcon = {
            Text(
                text = stringResource(R.string.accounts),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.width(16.dp))
        },
        content = {},
        tailIcon = {
            Text(
                text = account,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        height = 70.dp,
        showDivider = true,
        modifier = modifier
    )
}


@Preview(apiLevel = 34, showBackground = true)
@Composable
fun EditTransactionAccountPreview() {
    Column {
        Spacer(Modifier.height(100.dp))
        EditTransactionAccount(
            account = "Сбербанк"
        )
    }

}
