package com.sopt.now

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Constants.Companion.USER_DATA
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var userData: SignUpData? = null

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            userData =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.data?.getParcelableExtra(USER_DATA, SignUpData::class.java)
                else it.data?.getParcelableExtra(USER_DATA)
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
            showToast(R.string.success_login)
            Intent(this, MainActivity::class.java).apply {
                putExtra(USER_DATA, userData)
                startActivity(this)
            }
        } else showToast(R.string.fail_login)
    }

    private fun validateLogin(): Boolean =
        userData?.id == binding.etLoginId.text.toString() && userData?.password == binding.etLoginPw.text.toString()

    private fun showToast(message: Int) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}