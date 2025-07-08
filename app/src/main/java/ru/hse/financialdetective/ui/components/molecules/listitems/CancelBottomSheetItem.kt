package ru.hse.financialdetective.ui.components.molecules.listitems

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.financialdetective.ui.theme.ErrorColor

@Composable
fun CancelBottomSheetItem(
    onClick: () -> Unit
) {
    ListItem(
        leadIcon = {
            Icon(
                painter = painterResource(R.drawable.cancel_curchange),
                contentDescription = stringResource(R.string.choose_currency),
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
            Spacer(Modifier.width(16.dp))
        },
        content = {
            Text(
                text = stringResource(R.string.cancel),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        },
        height = 72.dp,
        color = ErrorColor,
        showDivider = true,
        isClickable = true,
        onClick = onClick
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun CancelBottomSheetItemPreview() {
    CancelBottomSheetItem({})
}