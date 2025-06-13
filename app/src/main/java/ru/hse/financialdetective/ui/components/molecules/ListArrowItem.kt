package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.coursework.financialdetective.ui.theme.GreyDark

@Composable
fun ListArrowItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ListItem(
        content = title,
        tailIcon = {
            Icon(
                painter = painterResource(R.drawable.trailing),
                contentDescription = "Перейти",
                modifier = Modifier.size(35.dp),
                tint = GreyDark
            )
        },
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
