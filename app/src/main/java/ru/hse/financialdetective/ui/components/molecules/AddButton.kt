package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.hse.coursework.financialdetective.R
import ru.hse.coursework.financialdetective.ui.theme.GreenBright

@Composable
fun AddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier
            .size(56.dp),
        onClick = onClick,
        shape = CircleShape,
        containerColor = GreenBright,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        )

    ) {
        Icon(
            painter = painterResource(R.drawable.plus),
            contentDescription = "Добавить",
            modifier = Modifier
                .size((15.56).dp),
            tint = Color.White
        )
    }
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun AddButtonPreview() {
    AddButton({})
}