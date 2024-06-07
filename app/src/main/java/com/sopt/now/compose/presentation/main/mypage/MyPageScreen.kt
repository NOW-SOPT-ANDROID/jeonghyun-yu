package com.sopt.now.compose.presentation.main.mypage

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.model.userinfo.UserInfo
import com.sopt.now.compose.utils.UiState

@Composable
fun MyPageScreen(
    myPageViewModel: MyPageViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by myPageViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        myPageViewModel.getUserInfo()
    }

    when (state) {
        is UiState.FAILURE -> {
            Toast.makeText(context, (state as UiState.FAILURE).errorMessage, Toast.LENGTH_SHORT).show()
        }
        UiState.LOADING -> { }
        is UiState.SUCCESS -> {
            MyPage((state as UiState.SUCCESS<*>).data as UserInfo)
        }
    }
}

@Composable
fun MyPage(userInfo: UserInfo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = userInfo.nickname.orEmpty(),
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))
        CustomText(text = stringResource(R.string.phone_number), fontSize = 20)
        CustomText(text = userInfo.phone.orEmpty(), fontSize = 15)

        Spacer(modifier = Modifier.height(30.dp))
        CustomText(text = stringResource(R.string.id), fontSize = 20)
        CustomText(text = userInfo.authenticationId.orEmpty(), fontSize = 15)
    }
}


@Composable
private fun CustomText(text: String, fontSize: Int) {
    Text(text = text, fontSize = fontSize.sp)
}
