package com.sopt.now.presentation.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.utils.Constants.Companion.USER_DATA
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.R
import com.sopt.now.model.SignUpData
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var userId: String? = null


    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            userId = it.data?.getStringExtra("user_id")
            /*userData =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.data?.getParcelableExtra(USER_DATA, SignUpData::class.java)
                else it.data?.getParcelableExtra(USER_DATA)*/
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSignup.setOnClickListener { navigateToSignup() }
            btnLogin.setOnClickListener { login() }
        }
    }

    private fun login() {
        // 로그인 서버 통신

    }

    private fun navigateToSignup() =
        Intent(this, SignupActivity::class.java).let { signupLauncher.launch(it) }

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