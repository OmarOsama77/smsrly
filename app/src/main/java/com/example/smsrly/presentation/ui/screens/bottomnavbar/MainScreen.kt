package com.example.smsrly.presentation.ui.screens.bottomnavbar

import MyAdds
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.home.Home
import com.example.smsrly.presentation.ui.screens.search.Search
import com.example.smsrly.presentation.ui.screens.sell.Sell
import com.example.smsrly.presentation.ui.screens.settings.Settings
import kotlinx.serialization.Serializable

@Serializable
data object MainRoute

@Composable
fun MainScreen(navController: NavController) {
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Search,
        BottomNavScreen.Adds,
        BottomNavScreen.MyAdds,
        BottomNavScreen.Settings
    )

    var selectedTab by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .background(Color.White)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, screen ->
                    val isSelected = selectedTab == index
                    Icon(
                        painter = painterResource(
                            id = if (isSelected) screen.selectedIcon else screen.icon
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { selectedTab = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())

        ) {
            when (selectedTab) {
                0 -> Home(navController)
                1 -> Search(navController)
                2 -> Sell(navController)
                3 -> MyAdds(navController)
                4 -> Settings(navController)
            }
        }
    }
}