package com.example.potterapp.ui.main.cells

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.potterapp.model.House

@Composable
fun HeaderCell (house: House) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(60.dp)
            .background(
                Brush.horizontalGradient(house.colors),
                AbsoluteCutCornerShape(
                    topLeftPercent = 50,
                    bottomLeftPercent = 50
                )
            )
            .padding(start = 32.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = house.text,
            style = MaterialTheme.typography.h4.copy(color = Color.White)
        )
    }
}