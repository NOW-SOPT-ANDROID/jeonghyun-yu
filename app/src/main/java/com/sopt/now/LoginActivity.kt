package com.sopt.now

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var data: SignUpData? = null

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val isSignup = it.data?.getBooleanExtra("isSignup", false) ?: false
        if (it.resultCode == RESULT_OK && isSignup) {
            data =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.data?.getSerializableExtra("data", SignUpData::class.java)
                else it.data?.getSerializableExtra("data") as SignUpData
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

    private fun navigateToSignup() =
        Intent(this, SignupActivity::class.java).let { signupLauncher.launch(it) }

    private fun login() {
        if (validateLogin()) {
            Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
            Intent(this, MainActivity::class.java)
                .putExtra("data", data)
                .apply { startActivity(this) }

        } else Toast.makeText(this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    private fun validateLogin(): Boolean =
        data?.id == binding.etLoginId.text.toString() && data?.password == binding.etLoginPw.text.toString()

}