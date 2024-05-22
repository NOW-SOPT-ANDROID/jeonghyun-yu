package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.utils.showToast

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater).apply { setContentView(root) }
        binding.btnSignup.setOnClickListener { signUp() }
        observeSignUp()
    }

    private fun signUp() = signUpViewModel.signUp(getSignUpRequestDto())

    private fun observeSignUp() {
        signUpViewModel.status.observe(this) {
            if (it) {
                showToast((R.string.success_signup).toString())
                backToLogin()
            } else {
                showToast(signUpViewModel.errorMessage ?: "")
            }
        }
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