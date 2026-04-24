package com.example.smsrly.presentation.ui.screens.auth.splash

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.auth.login.LoginRoute
import com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel.SplashEvents
import com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel.SplashViewModel
import com.example.smsrly.presentation.ui.screens.bottomnavbar.MainRoute
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute

@Composable
fun SplashScreen(navController: NavController) {

    val viewModel: SplashViewModel = hiltViewModel()


    LaunchedEffect(Unit) {

          viewModel.event.collect { event->
              when(event){
                  is SplashEvents.Success->{
                      delay(1000)
                      navController.navigate(MainRoute){
                          popUpTo(0){
                              inclusive = true
                          }
                      }
                  }
                  is SplashEvents.Failed->{
                      delay(1000)
                      navController.navigate(LoginRoute)
                  }
              }
          }

    }


    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animation)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }
}