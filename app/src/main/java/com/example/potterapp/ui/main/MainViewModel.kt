package com.example.potterapp.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potterapp.model.Actor
import com.example.potterapp.model.UIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    fun reloadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.reloadActors(uiModel)
        }
    }

    fun deleteCharacter(it: Actor) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.deleteCharacter(it)
        }
    }

    val characterList: Flow<List<Actor>> = mainRepository.getActors()

    val uiModel: MutableStateFlow<UIModel> = MutableStateFlow(UIModel.Loading)

    init {
        reloadCharacters()
    }

}