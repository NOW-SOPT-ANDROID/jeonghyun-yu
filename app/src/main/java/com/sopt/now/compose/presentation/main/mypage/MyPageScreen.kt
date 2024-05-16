package com.sopt.now.compose.presentation.main.mypage

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ApplicationClass.SharedPreferences.sSharedPreferences
import com.sopt.now.compose.R
import com.sopt.now.compose.model.userinfo.UserInfo
import com.sopt.now.compose.utils.Constants.Companion.MEMBER_ID

@Composable
fun MyPageScreen() {
    val userInfo = getUserInfo(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${userInfo?.nickname}",
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))
        customText(text = stringResource(R.string.phone_number), fontSize = 20)
        customText(text = "${userInfo?.phone}", fontSize = 15)

        Spacer(modifier = Modifier.height(30.dp))
        customText(text = stringResource(R.string.id), fontSize = 20)
        customText(text = "${userInfo?.authenticationId}", fontSize = 15)

    }
}

@Composable
private fun getUserInfo(context: Context): UserInfo? {
    val myPageViewModel = MyPageViewModel()
    myPageViewModel.getUserInfo()

    var userInfo by remember { mutableStateOf<UserInfo?>(null) }
    myPageViewModel.status.observe(LocalLifecycleOwner.current) {
        if (it) {
            userInfo = myPageViewModel.userInfo
        } else {
            Toast.makeText(context, myPageViewModel.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
    return userInfo
}



@Composable
private fun customText(text: String, fontSize: Int) {
    Text(text = text, fontSize = fontSize.sp)
}
