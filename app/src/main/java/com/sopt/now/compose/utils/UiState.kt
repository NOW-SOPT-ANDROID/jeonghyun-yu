package com.sopt.now.compose.utils

sealed class UiState<out T> {
    data object LOADING : UiState<Nothing>()
    data class SUCCESS<T>(
        val data: T? = null
    ) : UiState<T>()

    data class FAILURE(
        val errorMessage: String
    ) : UiState<Nothing>()
}