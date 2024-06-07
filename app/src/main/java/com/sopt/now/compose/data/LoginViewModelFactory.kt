package com.sopt.now.compose.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.presentation.auth.login.LoginViewModel
import com.sopt.now.compose.utils.ServicePool

class LoginViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository = AuthRepositoryImpl(ServicePool.loginService)
            return LoginViewModel(repository) as T
        } else {
            throw IllegalArgumentException("fail ${modelClass.name}")
        }
    }
}