package com.example.potterapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

object Colors {
    //Griffendel
    val grif_red = Color(0xFF740001)
    val grif_gold = Color(0xFFeeba30)
    //mardekar
    val sly_green = Color(0xFF2a623d)
    val sly_grey = Color(0xFFaaaaaa)
    //hollohat
    val rav_blue = Color(0xFF222f5b)
    val rav_white = Color(0xFFbebebe)
    //hugrabug
    val huff_yellow = Color(0xFFecb939)
    val huff_brown = Color(0xFF372e29)
    //ismeretlen
    val unk_white = Color(0xFFffffff)
    val unk_black = Color(0xFF222222)

    val cellBackground: Color
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) Color(0xbb202020) else Color(0xbbffffff)

    val whiteBlack: Color
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) Color.White else Color.Black
}