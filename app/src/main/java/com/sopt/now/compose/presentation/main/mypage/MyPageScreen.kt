package com.sopt.now.compose.presentation.main.mypage

import android.content.Context
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.model.userinfo.UserInfo

@Composable
fun MyPageScreen(context: Context, memberId: String?) {
    var userInfo by remember { mutableStateOf<UserInfo?>(null) }

    val myPageViewModel = MyPageViewModel()
    myPageViewModel.getUserInfo(memberId!!)
    myPageViewModel.userInfo.observe(LocalLifecycleOwner.current) {
        try {
            if (it.code == 200) {
                userInfo = myPageViewModel.userInfo.value?.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

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
private fun customText(text: String, fontSize: Int) {
    Text(text = text, fontSize = fontSize.sp)
}
