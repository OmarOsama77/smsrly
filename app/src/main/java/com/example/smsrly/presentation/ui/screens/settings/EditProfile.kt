package com.example.smsrly.presentation.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.components.CustomButton
import com.example.smsrly.presentation.ui.screens.components.CustomTextField
import com.example.smsrly.presentation.ui.screens.components.UserImage
import com.example.smsrly.presentation.ui.theme.Primary
import kotlinx.serialization.Serializable

@Serializable
data object EditProfileRoute

@Composable
fun EditProfile(navController: NavController) {
    val txt = remember{ mutableStateOf("") }
    Box() {
        Box(
            modifier = Modifier
                .background(
                    color = Primary, shape = RoundedCornerShape(
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                )
                .height(284.dp)
                .fillMaxWidth()
        ){
            IconButton({
                navController.popBackStack()
            }, modifier = Modifier.padding(top = 40.dp, start = 10.dp)) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 150.dp, start = 20.dp, end = 20.dp, bottom = 170.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .padding(top = 120.dp, start = 20.dp, end = 20.dp)
        ) {
           Box(
               modifier = Modifier.fillMaxWidth(),
               contentAlignment = Alignment.Center
           ){
               UserImage(null)
           }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text("Basic information ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                CustomTextField("First Name",txt.value,{})
                CustomTextField("Last Name",txt.value,{})
                CustomTextField("Email",txt.value,{})
                CustomTextField("Phone Number",txt.value,{})
                CustomButton("Save",{},Primary,true)
            }

        }

    }
}