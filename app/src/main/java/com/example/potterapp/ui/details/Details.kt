package com.example.potterapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.potterapp.R
import com.example.potterapp.theme.Colors
import com.example.potterapp.ui.header.Header

@Composable
fun Details(id: String?, viewModel: DetailsViewModel = hiltViewModel()) {
    val character by viewModel.character.collectAsState(initial = null)
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCharacter(id)
    })
    Column {
        Header(title = "", isBackButtonVisible = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (character == null) {
                CircularProgressIndicator(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
            } else {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(character!!.image) {
                            transformations(CircleCropTransformation())
                            placeholder(R.drawable.baseline_person_24)
                            error(R.drawable.baseline_person_24)
                        },
                        contentDescription = "",
                        modifier = Modifier
                            .size(240.dp)
                            .background(Colors.cellBackground, CircleShape),
                        alignment = Alignment.TopCenter,
                        colorFilter = if (character?.image.isNullOrEmpty()) {
                            ColorFilter.tint(Colors.whiteBlack)
                        } else null
                    )
                    CompositionLocalProvider(
                        LocalTextStyle provides MaterialTheme.typography.h4.copy(color = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentHeight()
                                .background(
                                    Brush.horizontalGradient(
                                        character?.house?.colors ?: listOf(
                                            Color.White,
                                            Color.White
                                        )
                                    ),
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 24.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = character?.name.orEmpty())
                            Text(text = character?.house?.text.orEmpty())
                        }
                    }

                }
                CompositionLocalProvider(
                    LocalTextStyle provides MaterialTheme.typography.subtitle1.copy(
                        color = Colors.whiteBlack,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    Column(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .wrapContentSize()
                            .background(
                                Colors.cellBackground,
                                RoundedCornerShape(24.dp)
                            )
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Species: ${character?.species?.takeIf { it.isNotEmpty() } ?: "N/A"}")
                        Text(text = "House: ${character?.house?.text?.takeIf { it.isNotEmpty() } ?: "N/A"}")
                        Text(text = "Ancestry: ${character?.ancestry?.takeIf { it.isNotEmpty() } ?: "N/A"}")
                        Text(text = "Date of birth: ${character?.dateOfBirth?.takeIf { it.isNotEmpty() } ?: "N/A"}")
                        Text(text = "Patronus: ${character?.patronus?.takeIf { it.isNotEmpty() } ?: "N/A"}")
                        Text(text = "Actor: ${character?.actor?.takeIf { it.isNotEmpty() } ?: "N/A"}")
                    }
                }
            }
        }

    }
}