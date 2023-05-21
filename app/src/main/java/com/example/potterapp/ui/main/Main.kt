package com.example.potterapp.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.potterapp.R
import com.example.potterapp.theme.Colors
import com.example.potterapp.ui.button.CustomButton
import com.example.potterapp.ui.header.Header
import com.example.potterapp.ui.main.cells.Cell
import com.example.potterapp.ui.main.cells.HeaderCell
import com.example.potterapp.ui.navigation.LocalNavController

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun Main(viewModel: MainViewModel = hiltViewModel()) {
    val errorMessage by viewModel.errorMessage.collectAsState(initial = null)
    val characters by viewModel.characterList.collectAsState(initial = listOf())
    val refreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.reloadCharacters() })
    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Header(
            title = "Harry Potter characters",
            isBackButtonVisible = false
        )
        errorMessage?.let {
            Text(
                text = it,
                color = Colors.whiteBlack,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.End)
                    .background(Colors.cellBackground, RoundedCornerShape(8.dp))
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                textAlign = TextAlign.End
            )
            CustomButton(icon = Icons.Default.Refresh,
                text = "Reload",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End),
                onClick = { viewModel.reloadCharacters() })
        }
        Box(
            Modifier.weight(1f)
                .pullRefresh(pullRefreshState)
        ) {
            if (characters.isEmpty()) {
                CircularProgressIndicator(
                    color = Colors.cellBackground,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    characters.groupBy {
                        it.house
                    }.forEach { (house, members) ->
                        stickyHeader {
                            HeaderCell(house = house)
                        }
                        items(members) {
                            Cell(it)
                        }
                    }

                }
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }


    }
}