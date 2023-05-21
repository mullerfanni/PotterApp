package com.example.potterapp.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potterapp.model.Actor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?>
        get() = _errorMessage

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    fun reloadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.reloadActors(_errorMessage, _isRefreshing)
        }
    }

    val characterList: Flow<List<Actor>> = mainRepository.getActors()

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            characterList.collectLatest {
                if (it.isEmpty()) {
                    mainRepository.reloadActors(_errorMessage, _isRefreshing)
                }
            }
        }
    }

}