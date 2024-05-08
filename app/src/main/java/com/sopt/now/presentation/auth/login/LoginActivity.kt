package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.presentation.auth.signup.SignupActivity
import com.sopt.now.utils.Constants.Companion.MEMBER_ID
import com.sopt.now.utils.NetworkUtil
import com.sopt.now.utils.showToast
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

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
        loginViewModel.status.observe(this) {
            if (it) {
                val memberId = loginViewModel.getMemberId()
                navigateToMain(memberId)
                showToast(memberId.toString())
            } else {
                showToast(loginViewModel.getErrorMessage() ?: "")
            }
        }
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

    private fun navigateToMain(userId: String?) {
        Intent(this, MainActivity::class.java).apply {
            putExtra(MEMBER_ID, userId)
            startActivity(this)
            finish()
        }
    }

}