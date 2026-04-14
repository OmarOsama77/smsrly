package com.example.smsrly.presentation.ui.screens.auth.login.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.auth.login.LoginRoute
import com.example.smsrly.presentation.ui.screens.auth.login.viewmodel.LoginEvents
import com.example.smsrly.presentation.ui.screens.auth.login.viewmodel.LoginState
import com.example.smsrly.presentation.ui.screens.auth.login.viewmodel.LoginViewModel
import com.example.smsrly.presentation.ui.screens.bottomnavbar.MainRoute
import com.example.smsrly.presentation.ui.screens.components.CustomButton
import com.example.smsrly.presentation.ui.screens.components.CustomTextField
import com.example.smsrly.presentation.ui.screens.components.PasswordTextField
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun LoginForm(modifier: Modifier = Modifier,navController: NavController) {
    var email by remember{ mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    val viewModel : LoginViewModel = hiltViewModel()
    val state = viewModel.loginState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loginEvents.collect {events ->
            when(events){
                is LoginEvents.Success -> {
                    navController.navigate(MainRoute){
                        popUpTo(LoginRoute){
                            inclusive = true
                        }
                    }
                }
                is LoginEvents.Failed ->{
                    Toast.makeText(navController.context,events.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    CustomTextField("Email",email,{email = it})
    Spacer(Modifier.height(20.dp))
    PasswordTextField("Password",pass,{pass = it})
    Spacer(Modifier.height(20.dp))
    if(state.value == LoginState.Loading){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(color = Primary)
        }
    }else{
        CustomButton("Log in",{
          viewModel.login(email,pass)
        },Primary,true)
    }
}