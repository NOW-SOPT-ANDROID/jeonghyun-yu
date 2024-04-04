package com.sopt.now.compose

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    private lateinit var data: SignUpData

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            data = getUserData(it)
        }
    }

    private fun getUserData(it: ActivityResult): SignUpData {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            it.data?.getSerializableExtra("userData", SignUpData::class.java)!!
        else it.data?.getSerializableExtra("userData") as SignUpData
    }

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
                        onLoginBtnClicked = {
                            navigateToMain()
                        },
                        onSignupBtnClicked = {
                            navigateToSignup()
                        }
                    )
                }
            }
        }
    }

    private fun navigateToSignup() =
        Intent(this, SignUpActivity::class.java).let { signupLauncher.launch(it) }

    private fun navigateToMain() {
        Intent(this, MainActivity::class.java)
            .putExtra("userData", data)
            .apply { startActivity(this) }
        /*Intent(this, MainActivity::class.java).apply {
            putExtra("userData", data)
        }.let { startActivity(it) }*/
    }

}

@Composable
fun ShowLogin(
    onLoginBtnClicked: () -> Unit,
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )

        Spacer(modifier = Modifier.weight(5f))

        Button(
            onClick = onLoginBtnClicked,
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
            onLoginBtnClicked = {},
            onSignupBtnClicked = {}
        )
    }
}