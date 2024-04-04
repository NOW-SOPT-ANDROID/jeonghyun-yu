package com.sopt.now.compose

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    private var data: SignUpData? = null

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            data =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.data?.getSerializableExtra("data", SignUpData::class.java)
                else it.data?.getSerializableExtra("data") as SignUpData
        }
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
                            Intent(this, MainActivity::class.java).apply {
                                putExtra("data", data)
                                startActivity(this)
                            }
                        },
                        onSignupBtnClicked = {
                            Intent(this, SignUpActivity::class.java).let {
                                signupLauncher.launch(it)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ShowLogin(
    onLoginBtnClicked: () -> Unit,
    onSignupBtnClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcom to SOPT",
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "ID",
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "아이디를 입력해주세요")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = "비밀번호",
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "비밀번호를 입력해주세요")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(5f))

        Button(
            onClick = onLoginBtnClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "로그인 하기")
        }

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            onClick = onSignupBtnClicked,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "회원가입 하기")
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