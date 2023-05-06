package com.example.potterapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.potterapp.model.UIModel

@Composable
fun Main (viewModel: MainViewModel = hiltViewModel()){
    val uiModel by viewModel.uiModel.collectAsState(initial = UIModel.Loading)
    val characters by viewModel.characterList.collectAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            when (val result = uiModel) {
                is UIModel.Loading -> {
                    CircularProgressIndicator()
                }
                is UIModel.Loaded -> {
                    Text(text = "Adatbázis frissítve")
                    Button(onClick = { viewModel.reloadCharacters() }) {
                        Text(text = "Reload")
                    }
                }
                is UIModel.Error -> {
                    Text(text = result.message)
                    Button(onClick = { viewModel.reloadCharacters() }) {
                        Text(text = "Reload")
                    }
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(characters){
                Text(text = it.name, modifier = Modifier.clickable {
                    viewModel.deleteCharacter(it)
                })
            }
        })
    }
}