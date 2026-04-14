package com.example.smsrly.presentation.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.auth.login.LoginRoute
import com.example.smsrly.presentation.ui.screens.components.DeleteDialogue
import com.example.smsrly.presentation.ui.screens.components.UserImage
import com.example.smsrly.presentation.ui.screens.components.UserImageShimmer
import com.example.smsrly.presentation.ui.screens.components.UserNameShimmer
import com.example.smsrly.presentation.ui.screens.settings.EditProfileRoute
import com.example.smsrly.presentation.ui.screens.settings.SettingsRoute
import com.example.smsrly.presentation.ui.screens.settings.state.SettingsUserState
import com.example.smsrly.presentation.ui.screens.settings.viewmodel.SettingsViewModel
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod

@Composable
fun SettingsBody(navController: NavController, viewModel: SettingsViewModel) {
    val user = viewModel.user.collectAsState()
    val state = viewModel.state.collectAsState()
    val showDeleteDialog = remember { mutableStateOf(false) }
    val showLoading = remember { mutableStateOf(false) }

    if (showDeleteDialog.value) {
        DeleteDialogue({
            showLoading.value = true
//            viewModel.deleteAccount()
//            if (state.value is SettingsUserState.AccountDeleted) {
//                viewModel.logOut()
//                navController.navigate(LoginRoute) {
//                    popUpTo(SettingsRoute) {
//                        inclusive = true
//                    }
//                }
//                showLoading.value = false
//            }
        }, {
            showDeleteDialog.value = false
        }, "Account")
    }
    Box(
        modifier = Modifier
            .padding(top = 150.dp, start = 20.dp, end = 20.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (state.value is SettingsUserState.Success) {
                    UserImage(user.value?.imageUrl)
                } else if (state.value is SettingsUserState.Loading) {
                    UserImageShimmer()
                }
                Spacer(Modifier.width(12.dp))

                if (state.value is SettingsUserState.Success) {
                    Text("${user.value?.firstName} ${user.value?.lastName}", fontSize = 20.sp)

                } else if (state.value is SettingsUserState.Loading) {
                    UserNameShimmer()
                }
            }

            Spacer(Modifier.height(12.dp))
            Text("Account Settings", fontSize = 16.sp, color = Color(0xFFADADAD))
            Spacer(Modifier.height(20.dp))
            SettingsRow("Edit Profile", R.drawable.rightarrow, {
                navController.navigate(EditProfileRoute)
            })
            Spacer(Modifier.height(20.dp))
            SettingsRow("Change Password", R.drawable.changepassword, {})
            Spacer(Modifier.height(20.dp))
            Text("More", fontSize = 16.sp, color = Color(0xFFADADAD))
            Spacer(Modifier.height(20.dp))
            SettingsRow("Contact us", R.drawable.customerservice, {})
            Spacer(Modifier.height(20.dp))
            SettingsRow("Log out", R.drawable.logout, {
                viewModel.logOut()
                navController.navigate(LoginRoute) {
                    popUpTo(SettingsRoute) {
                        inclusive = true
                    }
                }
            })
            Spacer(Modifier.height(20.dp))
            SettingsRow("Delete Account", R.drawable.delete, {
                showDeleteDialog.value = true
            })

        }
        if (showLoading.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingBox()
            }
        }
    }
}
