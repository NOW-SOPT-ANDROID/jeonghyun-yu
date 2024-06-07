package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.ApplicationClass.SharedPreferences.sSharedPreferences
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.presentation.auth.signup.SignupActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.utils.Constants.Companion.MEMBER_ID
import com.sopt.now.utils.LoginViewModelFactory
import com.sopt.now.utils.UiState
import com.sopt.now.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels { LoginViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            btnSignup.setOnClickListener { navigateToSignup() }
            btnLogin.setOnClickListener { login() }
        }

        observeLogin()
    }

    private fun login() {
        loginViewModel.postLogin(getLoginData())
    }

    private fun observeLogin() {
        loginViewModel.state.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is UiState.LOADING -> {}
                is UiState.SUCCESS -> {
                    sSharedPreferences.getString(MEMBER_ID, null)?.let { showToast(it) }
                    navigateToMain()
                }
                is UiState.FAILURE -> { showToast(state.errorMessage) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun getLoginData(): RequestLoginDto {
        with(binding) {
            val id = etLoginId.text.toString().trim()
            val password = etLoginPw.text.toString().trim()

            return RequestLoginDto(
                authenticationId = id,
                password = password
            )
        }
    }

    private fun navigateToSignup() =
        Intent(this, SignupActivity::class.java).apply { startActivity(this) }

    private fun navigateToMain() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }
}
