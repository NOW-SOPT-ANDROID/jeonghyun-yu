package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Constants.Companion.MAX_ID_LENGTH
import com.sopt.now.Constants.Companion.MAX_PASSWORD_LENGTH
import com.sopt.now.Constants.Companion.MBTI_LENGTH
import com.sopt.now.Constants.Companion.MIN_ID_LENGTH
import com.sopt.now.Constants.Companion.MIN_PASSWORD_LENGTH
import com.sopt.now.Constants.Companion.USER_DATA
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener { signup() }
    }

    private fun signup() {
        if (validateSignup()) {
            val userData = SignUpData(
                binding.etSignupId.text.toString(),
                binding.etSignupPw.text.toString(),
                binding.etSignupNickname.text.toString(),
                binding.etSignupMbti.text.toString()
            )

            showToast(R.string.success_signup)
            Intent(this, LoginActivity::class.java).apply {
                putExtra(USER_DATA, userData)
                setResult(RESULT_OK, this)
                finish()
            }
        }
    }

    private fun validateSignup(): Boolean =
        validateId() && validatePassword() && validateNickname() && validateMBTI()

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
        require(binding.etSignupMbti.length() == MBTI_LENGTH) {
            showToast(R.string.fail_sign_up_mbti)
            return false
        }
        return true
    }

    private fun showToast(message: Int) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}