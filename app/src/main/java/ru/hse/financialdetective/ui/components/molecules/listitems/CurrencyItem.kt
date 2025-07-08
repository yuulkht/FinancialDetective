package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.GreenLight
import ru.hse.financialdetective.ui.theme.GreyDark

@Composable
fun CurrencyItem(
    currency: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isClickable: Boolean = false,

    ) {
    ListItem(
        content = {
            Text(
                text = stringResource(R.string.currency),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        tailString = currency,
        tailIcon = {
            Spacer(Modifier.width(16.dp))
            Icon(
                painter = painterResource(R.drawable.more_vert),
                contentDescription = stringResource(R.string.choose_currency),
                modifier = Modifier.size(24.dp),
                tint = GreyDark
            )
        },
        color = GreenLight,
        height = 56.dp,
        showDivider = false,
        isClickable = isClickable,
        onClick = onClick,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun CurrencyItemPreview() {
    CurrencyItem("₽")
}