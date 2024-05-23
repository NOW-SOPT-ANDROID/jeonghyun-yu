package com.sopt.now.compose.utils

sealed class UiState {
    data object LOADING : UiState()
    data class SUCCESS<T>(
        val data: T? = null
    ) : UiState()

    data class FAILURE(
        val errorMessage: String
    ) : UiState()
}