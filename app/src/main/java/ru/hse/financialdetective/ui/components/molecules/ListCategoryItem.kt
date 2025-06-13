package ru.hse.financialdetective.ui.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.hse.coursework.financialdetective.ui.theme.GreenLight

@Composable
fun ListCategoryItem(
    emoji: String,
    title: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        leadIcon = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(GreenLight, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                    fontSize = 18.sp
                )
            }
            Spacer(Modifier.width(18.dp))
        },
        content = title,
        showDivider = true,
        modifier = modifier
    )
}

@Preview(apiLevel = 34, showBackground = true)
@Composable
fun ListCategoryItemPreview() {
    ListCategoryItem(
        emoji = "💰",
        title = "Зарплата"
    )
}
