package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.sopt.now.compose.Constants.Companion.MAX_ID_LENGTH
import com.sopt.now.compose.Constants.Companion.MAX_PASSWORD_LENGTH
import com.sopt.now.compose.Constants.Companion.MBTI_LENGTH
import com.sopt.now.compose.Constants.Companion.MIN_ID_LENGTH
import com.sopt.now.compose.Constants.Companion.MIN_PASSWORD_LENGTH
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignUpActivity : ComponentActivity() {
    lateinit var userData: SignUpData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    showSignup(onSignupBtnClicked = { id, password, nickname, mbti ->
                        userData = SignUpData(id, password, nickname, mbti)
                        if (validateSignup()) {
                            showToast(R.string.success_signup)
                            navigateToLogin()
                            finish()
                        }
                    })
                }
            }
        }
    }

    private fun navigateToLogin() {
        Intent(this, LoginActivity::class.java).apply {
            putExtra("userData", userData)
            setResult(RESULT_OK, this)
        }
    }

    private fun validateSignup(): Boolean =
        validateId() && validatePassword() && validateNickname() && validateMBTI()

    private fun validateId(): Boolean {
        require(userData.id.length in MIN_ID_LENGTH..MAX_ID_LENGTH) {
            showToast(R.string.fail_sign_up_id)
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        require(userData.password.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH) {
            showToast(R.string.fail_sign_up_password)
            return false
        }
        return true
    }

    private fun validateNickname(): Boolean {
        require(userData.nickname.trim().isNotEmpty()) {
            showToast(R.string.fail_sign_up_nickname)
            return false
        }
        return true
    }

    private fun validateMBTI(): Boolean {
        require(userData.mbti.length == MBTI_LENGTH) {
            showToast(R.string.fail_sign_up_mbti)
            return false
        }
        return true
    }

    private fun showToast(message: Int) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun showSignup(
    onSignupBtnClicked: (String, String, String, String) -> Unit
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.sign_up),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = stringResource(R.string.nickname),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            placeholder = { Text(text = stringResource(R.string.input_nickname)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = stringResource(R.string.mbti),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = mbti,
            onValueChange = { mbti = it },
            placeholder = { Text(text = stringResource(R.string.input_mbti)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(5f))

        Button(
            onClick = { onSignupBtnClicked(id, password, nickname, mbti) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.new_sign_up)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    NOWSOPTAndroidTheme {
        showSignup(onSignupBtnClicked = { _, _, _, _ -> })
    }
}