package com.example.smsrly.presentation.ui.screens.auth.otp.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel.OtpEvent
import com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel.OtpState
import com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel.OtpViewModel
import com.example.smsrly.presentation.ui.screens.auth.signup.SignupRoute
import com.example.smsrly.presentation.ui.screens.components.CustomButton
import com.example.smsrly.presentation.ui.screens.components.CustomTextField
import com.example.smsrly.presentation.ui.theme.Primary


@Composable
fun OtpForm(viewModel: OtpViewModel, navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val state = viewModel.otpState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is OtpEvent.Success -> {
                    Toast.makeText(navController.context, event.message, Toast.LENGTH_SHORT).show()

                    navController.navigate(
                        SignupRoute(
                          firstName,
                            lastName,
                            email,
                            viewModel.selectedImage.value?.toString(),
                            viewModel.getUserLat(),
                            viewModel.getUserLong()
                        )
                    )

                }

                is OtpEvent.Failed ->
                    Toast.makeText(navController.context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text("Create an account", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(45.dp))
        UserImagePicker(viewModel)
        Spacer(Modifier.height(45.dp))

        CustomTextField("First Name", firstName, { firstName = it })
        Spacer(Modifier.height(15.dp))
        CustomTextField("Last Name", lastName, { lastName = it })
        Spacer(Modifier.height(15.dp))
        CustomTextField("Email", email, { email = it })
        Spacer(Modifier.height(15.dp))

        Spacer(Modifier.height(15.dp))


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (state.value is OtpState.Loading) {

                CircularProgressIndicator(color = Primary)

            } else {
                CustomButton(txt = "send otp", {
                    viewModel.sendOtp(
                        firstName,
                        lastName,
                        email,

                    )


                }, Primary, true)
            }
        }
    }


}