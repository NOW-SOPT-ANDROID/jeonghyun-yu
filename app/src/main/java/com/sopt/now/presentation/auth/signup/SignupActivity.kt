package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.utils.UiState
import com.sopt.now.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater).apply { setContentView(root) }
        binding.btnSignup.setOnClickListener { signUp() }
        observeSignUp()
    }

    private fun signUp() = signUpViewModel.signUp(getSignUpRequestDto())

    private fun observeSignUp() {
        signUpViewModel.state.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is UiState.LOADING -> {}
                is UiState.SUCCESS -> {
                    showToast(getString(R.string.success_signup))
                    backToLogin()
                }
                is UiState.FAILURE -> { showToast(state.errorMessage) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun backToLogin() {
        showToast(getString(R.string.success_signup))
        finish()
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        with(binding) {
            val id = etSignupId.text.trim().toString()
            val password = etSignupPw.text.trim().toString()
            val nickname = etSignupNickname.text.trim().toString()
            val phoneNumber = etSignupPhoneNumber.text.trim().toString()

            return RequestSignUpDto(
                authenticationId = id,
                password = password,
                nickname = nickname,
                phone = phoneNumber
            )
        }
    }
}