package com.example.potterapp.ui.main.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.potterapp.R
import com.example.potterapp.model.Actor
import com.example.potterapp.theme.Colors
import com.example.potterapp.ui.navigation.LocalNavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Cell (actor: Actor) {
    val navController = LocalNavController.current

    Card(
    elevation = 4.dp,
    onClick = {
        navController.navigate("details_page/${actor.id}") {
            launchSingleTop = true
        }
    },
    backgroundColor = Colors.cellBackground,
    modifier = Modifier.padding(horizontal = 8.dp),
    shape = CircleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(actor.image) {
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.baseline_person_24)
                    error(R.drawable.baseline_person_24)
                },
                contentDescription = "",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(60.dp),
                alignment = Alignment.TopCenter,
                colorFilter = if (actor.image.isEmpty()) {
                    ColorFilter.tint(Colors.whiteBlack)
                } else null
            )
            Text(
                text = actor.name,
                color = Colors.whiteBlack,
                style = MaterialTheme.typography.h6
            )
        }
    }
}