package com.sopt.now.presentation.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.utils.Constants.Companion.MAX_ID_LENGTH
import com.sopt.now.utils.Constants.Companion.MAX_PASSWORD_LENGTH
import com.sopt.now.utils.Constants.Companion.MBTI_LENGTH
import com.sopt.now.utils.Constants.Companion.MIN_ID_LENGTH
import com.sopt.now.utils.Constants.Companion.MIN_PASSWORD_LENGTH
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.model.signup.RequestSignUpDto
import com.sopt.now.model.signup.ResponseSignUpDto
import com.sopt.now.presentation.auth.login.LoginActivity
import com.sopt.now.utils.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater).apply { setContentView(root) }
        binding.btnSignup.setOnClickListener {
            signUp()
        }
        observeSignUp()
    }

    private fun signUp() {
        val data = getSignUpRequestDto()
        signUpViewModel.signUp(data)
    }

    private fun observeSignUp() {
        signUpViewModel.status.observe(this) {
            if (it) {
                showToast(R.string.success_signup)
                navigateToLogin()
            }
        }
    }


    private fun navigateToLogin() {
        Intent(this, LoginActivity::class.java).apply { finish() }
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

    private fun validateSignup(): Boolean =
        validateId() && validatePassword() && validateNickname() //&& validateMBTI()

    private fun validateId(): Boolean {
        require(binding.etSignupId.length() in MIN_ID_LENGTH..MAX_ID_LENGTH) {
            showToast(R.string.fail_sign_up_id)
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        require(binding.etSignupPw.length() in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH) {
            showToast(R.string.fail_sign_up_password)
            return false
        }
        return true
    }

    private fun validateNickname(): Boolean {
        require(
            binding.etSignupNickname.text.trim().isNotEmpty()
        ) {
            showToast(R.string.fail_sign_up_nickname)
            return false
        }
        return true
    }

    private fun validateMBTI(): Boolean {
        require(binding.etSignupPhoneNumber.length() == MBTI_LENGTH) {
            showToast(R.string.fail_sign_up_mbti)
            return false
        }
        return true
    }

    private fun showToast(message: Int) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}