package com.example.potterapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potterapp.model.Actor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val repository: DetailsRepository): ViewModel() {

    private val _character : MutableStateFlow<Actor?> = MutableStateFlow(null)
    val character : Flow<Actor?> get() = _character

    fun getCharacter(id: String?) {
        viewModelScope.launch (Dispatchers.IO) {
            _character.emit(repository.getCharacter(id))
        }
    }
}