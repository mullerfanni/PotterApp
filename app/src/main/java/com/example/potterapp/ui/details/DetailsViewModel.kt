package com.example.potterapp.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.potterapp.model.Actor
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
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
            val hpCharacter = repository.getCharacter(id)
            Firebase.analytics.logEvent("HP_CHARACTER", Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID, hpCharacter?.id);
                putString(FirebaseAnalytics.Param.ITEM_NAME, hpCharacter?.name);
            })
            _character.emit(hpCharacter)
        }
    }
}