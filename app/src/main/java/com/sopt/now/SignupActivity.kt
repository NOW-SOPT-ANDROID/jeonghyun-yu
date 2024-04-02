package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            val data = SignUpData(
                binding.etSignupId.text.toString(),
                binding.etSignupPw.text.toString(),
                binding.etSignupNickname.text.toString(),
                binding.etSignupMbti.text.toString()
            )

            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
            setResult(
                RESULT_OK, Intent()
                    .putExtra("isSignup", true)
                    .putExtra("data", data)
            )
            finish()
        }
    }

    private fun validateSignup(): Boolean =
        validateId() && validatePassword() && validateNickname() && validateMBTI()

    private fun validateId(): Boolean {
        require(binding.etSignupId.length() in 6..10) {
            Toast.makeText(this, "ID는 6글자 이상 10글자 이내로 작성해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        require(binding.etSignupPw.length() in 8..10) {
            Toast.makeText(this, "비밀번호는 8글자 이상 12글자 이내로 작성해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateNickname(): Boolean {
        require(
            binding.etSignupNickname.length() >= 1 && binding.etSignupNickname.text.toString().trim()
                .isNotEmpty()
        ) {
            Toast.makeText(this, "닉네임은 한 글자 이상어야 합니다", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateMBTI(): Boolean {
        require(binding.etSignupMbti.length() == 4) {
            Toast.makeText(this, "16가지 MBTI유형 중 해당되는 것을 작성해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}