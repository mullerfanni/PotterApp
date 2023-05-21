package com.example.potterapp.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material.Typography
import androidx.compose.ui.unit.dp
import com.example.potterapp.R

@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    val shapes = MaterialTheme.shapes.copy(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(32.dp),
        large = RoundedCornerShape(48.dp)
    )

    val typography = Typography(
        defaultFontFamily = FontFamily(Font(R.font.readex))
    )

    MaterialTheme(shapes = shapes, typography = typography, content = content)
}