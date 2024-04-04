package com.sopt.now.compose

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.Constants.Companion.USER_DATA
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    private lateinit var userData: SignUpData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    userData = getUserData()
                    showMain(userData)
                }
            }
        }
    }

    private fun getUserData(): SignUpData {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(USER_DATA, SignUpData::class.java)!!
        else intent.getParcelableExtra(USER_DATA)!!
    }
}

@Composable
fun showMain(userData: SignUpData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${userData.id}",
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.mbti),
            fontSize = 20.sp
        )
        Text(
            text = "${userData.mbti}",
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.id),
            fontSize = 20.sp
        )
        Text(
            text = "${userData.id}",
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.password),
            fontSize = 20.sp
        )
        Text(
            text = "${userData.password}",
            fontSize = 15.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        showMain(userData = SignUpData("id","password", "nickname", "mbti"))
    }
}