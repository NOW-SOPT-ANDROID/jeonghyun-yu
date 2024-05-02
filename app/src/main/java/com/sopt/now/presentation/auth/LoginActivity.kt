package com.sopt.now.presentation.auth

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.R
import com.sopt.now.model.SignUpData
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.model.login.RequestLoginDto
import com.sopt.now.model.login.ResponseLoginDto
import com.sopt.now.utils.ServicePool
import com.sopt.now.utils.ServicePool.authService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    //private var userId: String? = null
    private val loginService by lazy { ServicePool.loginService }

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            //userId = it.data?.getStringExtra("user_id")
            /*userData =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.data?.getParcelableExtra(USER_DATA, SignUpData::class.java)
                else it.data?.getParcelableExtra(USER_DATA)*/
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            btnSignup.setOnClickListener { navigateToSignup() }
            btnLogin.setOnClickListener { login() }
        }
    }

    private fun login() {
        // 로그인 서버 통신
        //Toast.makeText(this, "id는 $userId", Toast.LENGTH_SHORT).show()
        //Log.d("olivia id", userId.toString())

        val loginRequest = getLoginData()
        loginService.login(loginRequest).enqueue(object: Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    Toast.makeText(this@LoginActivity, "로그인 성공 id는 $userId", Toast.LENGTH_SHORT).show()
                    Log.d("olivia id", userId.toString())

                    navigateToMain(userId)
                    finish()
                } else {
                    val error = response.message()
                    Toast.makeText(this@LoginActivity, "로그인 실패 $error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "서버 에러 발생 t: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getLoginData() : RequestLoginDto {
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
        Intent(this, SignupActivity::class.java).let { signupLauncher.launch(it) }

    private fun navigateToMain(userId: String?) {
        Intent(this, MainActivity::class.java).apply {
            putExtra("memberId", userId)
            startActivity(this) }
    }

    /*private fun login() {
        if (validateLogin()) {
            showToast(R.string.success_login)
            *//*Intent(this, MainActivity::class.java).apply {
                putExtra(USER_DATA, userData)
                startActivity(this)
            }*//*
        } else showToast(R.string.fail_login)
    }*/

    /*private fun validateLogin(): Boolean =
        userData?.id == binding.etLoginId.text.toString() && userData?.password == binding.etLoginPw.text.toString()*/

    private fun showToast(message: Int) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}