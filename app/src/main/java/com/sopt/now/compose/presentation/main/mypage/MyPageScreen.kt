package com.sopt.now.compose.presentation.main.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.model.SignUpData

@Composable
fun MyPageScreen(userData: SignUpData?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${userData?.id}",
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))
        customText(text = stringResource(R.string.mbti), fontSize = 20)
        customText(text = "${userData?.mbti}", fontSize = 15)

        Spacer(modifier = Modifier.height(30.dp))
        customText(text = stringResource(R.string.id), fontSize = 20)
        customText(text = "${userData?.id}", fontSize = 15)

        Spacer(modifier = Modifier.height(30.dp))
        customText(text = stringResource(R.string.password), fontSize = 20)
        customText(text = "${userData?.password}", fontSize = 15)
    }
}

@Composable
private fun customText(text: String, fontSize: Int) {
    Text(text = text, fontSize = fontSize.sp)
}
