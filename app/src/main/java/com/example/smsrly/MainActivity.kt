package com.example.smsrly

import MyAdds
import MyAddsRoute
import Otp
import OtpRoute
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smsrly.presentation.ui.screens.auth.login.Login
import com.example.smsrly.presentation.ui.screens.auth.login.LoginRoute
import com.example.smsrly.presentation.ui.screens.auth.signup.signup
import com.example.smsrly.presentation.ui.screens.auth.splash.SplashRoute
import com.example.smsrly.presentation.ui.screens.auth.splash.SplashScreen
import com.example.smsrly.presentation.ui.screens.bottomnavbar.MainRoute
import com.example.smsrly.presentation.ui.screens.bottomnavbar.MainScreen
import com.example.smsrly.presentation.ui.screens.chats.Chats
import com.example.smsrly.presentation.ui.screens.chats.ChatsRoute
import com.example.smsrly.presentation.ui.screens.chats.conversation.Conversation
import com.example.smsrly.presentation.ui.screens.chats.conversation.ConversationRoute
import com.example.smsrly.presentation.ui.screens.chats.conversation.conversation
import com.example.smsrly.presentation.ui.screens.home.Home
import com.example.smsrly.presentation.ui.screens.home.HomeRoute
import com.example.smsrly.presentation.ui.screens.locationPicker.MapPicker
import com.example.smsrly.presentation.ui.screens.locationPicker.MapPickerRoute
import com.example.smsrly.presentation.ui.screens.search.Search
import com.example.smsrly.presentation.ui.screens.search.SearchRoute
import com.example.smsrly.presentation.ui.screens.sell.Sell
import com.example.smsrly.presentation.ui.screens.sell.SellRoute
import com.example.smsrly.presentation.ui.screens.settings.EditProfile
import com.example.smsrly.presentation.ui.screens.settings.EditProfileRoute
import com.example.smsrly.presentation.ui.screens.settings.Settings
import com.example.smsrly.presentation.ui.screens.settings.SettingsRoute
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetailsRoute
import com.example.smsrly.presentation.ui.theme.slideInFromLeft
import com.example.smsrly.presentation.ui.theme.slideInFromRight
import com.example.smsrly.presentation.ui.theme.slideOutToLeft
import com.example.smsrly.presentation.ui.theme.slideOutToRight
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = SplashRoute,
            ) {
                composable<MainRoute> {
                    MainScreen(navController)
                }
                composable<MapPickerRoute> {
                    MapPicker(navController)
                }
                composable<LoginRoute> {
                    Login(navController)
                }

                signup(navController)
                conversation(navController)
                composable<OtpRoute> {
                    Otp(navController)
                }
                composable<SplashRoute> {
                    SplashScreen(navController)
                }
                composable<HomeRoute> {
                    Home(navController)
                }
                composable<SearchRoute> { Search(navController) }
                composable<SettingsRoute> { Settings(navController) }
                composable<SellRoute> { Sell(navController) }
                composable<MyAddsRoute> { MyAdds(navController) }
                ShowDetailsRoute(navController)
                composable<EditProfileRoute> { EditProfile(navController) }

                composable<ChatsRoute>(
                    enterTransition = { slideInFromRight },
                    exitTransition = { slideOutToLeft },
                    popEnterTransition = { slideInFromLeft },
                    popExitTransition = { slideOutToRight }
                ) {
                    Chats(navController)
                }
            }
        }
    }
}

