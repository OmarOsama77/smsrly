package com.example.smsrly.presentation.ui.screens.auth.signup

import OtpTextField
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.notes.view.components.CustomIconButton
import com.example.smsrly.domain.models.User
import com.example.smsrly.presentation.ui.screens.auth.login.LoginRoute
import com.example.smsrly.presentation.ui.screens.auth.signup.viewmodel.SignupEvents
import com.example.smsrly.presentation.ui.screens.auth.signup.viewmodel.SignupState
import com.example.smsrly.presentation.ui.screens.auth.signup.viewmodel.SignupViewModel
import com.example.smsrly.presentation.ui.screens.components.CustomButton
import com.example.smsrly.presentation.ui.screens.components.CustomLoadingIndicator
import com.example.smsrly.presentation.ui.screens.components.PasswordTextField
import com.example.smsrly.presentation.ui.screens.components.PhoneNumberTextField
import com.example.smsrly.presentation.ui.theme.Primary
import com.example.smsrly.utility.UserNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class SignupRoute(
    val firstName: String,
    val lastName: String,
    val email: String,
    val userImage: String?,
    val latitude: Double,
    val longitude: Double
)


fun NavGraphBuilder.signup(navController: NavController) {
    composable<SignupRoute>(typeMap = mapOf(typeOf<User>() to UserNavType)) { backStackEntry ->
        val route = backStackEntry.toRoute<SignupRoute>()
        Signup(
            navController,
            route.firstName,
            route.lastName,
            route.email,
            route.userImage,
            route.latitude,
            route.longitude
        )
    }
}

@Composable
fun Signup(
    navController: NavController,
    firstName: String,
    lastName: String,
    email: String,
    userImage: String?,
    latitude: Double,
    longitude: Double
) {
    val context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    val viewModel: SignupViewModel = hiltViewModel()
    val state = viewModel.signupState.collectAsState()
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        viewModel.events.collect { events ->
            when (events) {
                is SignupEvents.SignupSuccess -> {
                    Toast.makeText(context, events.message, Toast.LENGTH_SHORT).show()
                    navController.navigate(LoginRoute)
                }

                is SignupEvents.SignupFailed -> {
                    Toast.makeText(context, events.message, Toast.LENGTH_SHORT).show()

                }

                is SignupEvents.ImageUploadFailed -> {
                    Toast.makeText(context, events.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(top = 40.dp, start = 25.dp, end = 25.dp)
            .imePadding()
            .verticalScroll(scrollState)
    ) {
        CustomIconButton(icon = Icons.Filled.KeyboardArrowLeft, {
            navController.popBackStack()
        })
        Spacer(Modifier.height(20.dp))
        Text("Verification", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(Modifier.height(20.dp))
        Text("We've sent you the verification code check your email")
        Spacer(Modifier.height(20.dp))
        PasswordTextField("Password", password, { password = it })
        Spacer(Modifier.height(25.dp))
        PasswordTextField("Confirm password", confirmPassword, { confirmPassword = it })
        Spacer(Modifier.height(25.dp))
        PhoneNumberTextField(phoneNumber, {
            if (it.length <= 11 && it.all { it.isDigit() }) {
                phoneNumber = it
            }
        })
        Spacer(Modifier.height(25.dp))
        OtpTextField(otp, {
            if (it.length <= 4) {
                otp = it
            }
        })
        Spacer(Modifier.height(20.dp))

        if (otp.length < 4) {
            CustomButton("Confirm", {

            }, Color.Gray, false)
        } else {
            if (state.value is SignupState.Loading) {
                CustomLoadingIndicator()
            } else {
                CustomButton("Confirm", {

                    viewModel.signup(
                        firstName,
                        lastName,
                        email,
                        phoneNumber,
                        latitude,
                        longitude,
                        password,
                        confirmPassword,
                        otp,
                        userImage
                    )
                }, Primary, true)
            }
        }
    }
}