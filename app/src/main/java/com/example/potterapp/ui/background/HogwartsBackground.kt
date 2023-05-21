package com.example.potterapp.ui.background

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.potterapp.R

@Composable
fun HogwartsBackground() {
    val image = if (isSystemInDarkTheme()) {
        R.drawable.hogwarts_dark
    } else {
        R.drawable.hogwarts_light
    }
    Image(
        painter = painterResource(id = image),
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
    )
}
