package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.size
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
import ru.hse.financialdetective.ui.theme.GreyDark

@Composable
fun ListArrowItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ListItem(
        content = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        tailIcon = {
            Icon(
                painter = painterResource(R.drawable.arrow),
                contentDescription = stringResource(R.string.go_to),
                modifier = Modifier.size(24.dp),
                tint = GreyDark
            )
        },
        height = 56.dp,
        showDivider = true,
        onClick = onClick,
        isClickable = true,
        modifier = modifier
    )
}


@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListArrowItemPreview() {
    ListArrowItem(
        title = "Основной цвет",
        onClick = {}
    )
}
