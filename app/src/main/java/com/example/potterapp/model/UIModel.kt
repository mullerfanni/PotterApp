package com.example.potterapp.model

sealed class UIModel {
    object Loading: UIModel()

    object Loaded: UIModel()

    data class Error(val message: String): UIModel()


}