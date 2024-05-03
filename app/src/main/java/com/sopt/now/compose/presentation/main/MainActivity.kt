package com.sopt.now.compose.presentation.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.model.BottomNavigationItem
import com.sopt.now.compose.model.SignUpData
import com.sopt.now.compose.presentation.main.home.HomeScreen
import com.sopt.now.compose.presentation.main.mypage.MyPageScreen
import com.sopt.now.compose.presentation.main.mypage.MyPageViewModel
import com.sopt.now.compose.presentation.main.search.SearchScreen
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.compose.utils.Constants.Companion.MEMBER_ID

class MainActivity : ComponentActivity() {
    private var memberId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    memberId = getUserData()
                    showMain(memberId)
                }
            }
        }
    }

    private fun getUserData(): String? {
        return intent.getStringExtra(MEMBER_ID)
    }
}

@Composable
fun showMain(memberId: String?) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var presses by remember { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavigationItem(
            icon = Icons.Filled.Home,
            label = "Home"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Search,
            label = "Search"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Person,
            label = "Profile"
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (selectedItem) {
                0 -> {
                    HomeScreen()
                }

                1 -> {
                    SearchScreen()
                }

                2 -> {
                    MyPageScreen(LocalContext.current, memberId)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        showMain(memberId = null)
    }
}