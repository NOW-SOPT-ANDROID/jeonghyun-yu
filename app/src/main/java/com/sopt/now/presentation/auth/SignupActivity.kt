package com.sopt.now.presentation.auth

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.sopt.now.utils.BaseResponse
import com.sopt.now.utils.ServicePool
import com.sopt.now.utils.ServicePool.authService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val authService by lazy { ServicePool.authService }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.btnSignup.setOnClickListener { signup() }
        initViews()
    }

    private fun initViews() {
        binding.btnSignup.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    Log.d("olivia success", "success")
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]

                    Toast.makeText(
                        this@SignupActivity,
                        "회원가입 성공 유저의 ID는 $userId 입니둥",
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d("SignUp", "data: $data, userId: $userId")

                    // 나는 81번 0703olivia
                    if (validateSignup()) successSignUp(userId!!)

                } else {
                    val error = response.message() // 에러 메세지가 안뜸
                    Log.d("olivia error", error)
                    Toast.makeText(
                        this@SignupActivity,
                        "회원가입 실패 $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }

            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Log.d("olivia server error", "error ${t.message}")
                Toast.makeText(this@SignupActivity, "서버 에러 발생 t: ${t.message}", Toast.LENGTH_SHORT).show()
            }


        })

    }


    private fun successSignUp(userId: String) {
        Intent(this, LoginActivity::class.java).apply {
            putExtra("user_id", userId)
            setResult(RESULT_OK, this)
            //finish()
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etSignupId.text.toString()
        val password = binding.etSignupPw.text.toString()
        val nickname = binding.etSignupNickname.text.toString()
        val phoneNumber = binding.etSignupPhoneNumber.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }

    /*private fun signup() {
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
    }*/

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