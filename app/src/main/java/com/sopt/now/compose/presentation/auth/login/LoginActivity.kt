package com.sopt.now.compose.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.model.login.RequestLoginDto
import com.sopt.now.compose.presentation.auth.signup.SignUpActivity
import com.sopt.now.compose.presentation.main.MainActivity
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.compose.utils.Constants.Companion.MEMBER_ID
import java.lang.Exception


class LoginActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowLogin(
                        onLoginBtnClicked = { id, password ->
                            login(RequestLoginDto(id, password))
                        },
                        onSignupBtnClicked = {
                            navigateToSignup()
                        }
                    )
                }

                observeLogin()
            }
        }
    }

    private fun login(data: RequestLoginDto) {
        loginViewModel.postLogin(data)
    }

    private fun observeLogin() {
        loginViewModel.login.observe(this) {
            try {
                if (it.code == 200) {
                    val memberId = loginViewModel.getMemberId()
                    navigateToMain(memberId)
                    showToast(memberId.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun navigateToSignup() =
        Intent(this, SignUpActivity::class.java).apply { startActivity(this) }

    private fun navigateToMain(memberId: String?) {
        Intent(this, MainActivity::class.java).apply {
            putExtra(MEMBER_ID, memberId)
            startActivity(this)
            finish()
        }
    }

}

@Composable
fun ShowLogin(
    onLoginBtnClicked: (String, String) -> Unit,
    onSignupBtnClicked: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.welcome_to_sopt),
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.id),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = id,
            onValueChange = { id = it },
            placeholder = { Text(text = stringResource(R.string.input_id)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = stringResource(R.string.password),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = stringResource(R.string.input_password)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.weight(5f))

        Button(
            onClick = { onLoginBtnClicked(id, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.login))
        }

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            onClick = onSignupBtnClicked,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.new_sign_up))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        ShowLogin(
            onLoginBtnClicked = { _, _ -> },
            onSignupBtnClicked = {}
        )
    }
}