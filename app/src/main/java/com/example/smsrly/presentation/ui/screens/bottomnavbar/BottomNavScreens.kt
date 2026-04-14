package com.example.smsrly.presentation.ui.screens.bottomnavbar

import com.example.smsrly.R

sealed class BottomNavScreen(
    val route : String,
    val icon : Int,
    val selectedIcon: Int
){
    object Home : BottomNavScreen("Home", R.drawable.home,R.drawable.homeselected)
    object Search : BottomNavScreen("Search",R.drawable.search,R.drawable.searchselected)
    object Adds : BottomNavScreen("Adds",R.drawable.add,R.drawable.addselected)
    object MyAdds : BottomNavScreen("MyAdds",R.drawable.savebottom,R.drawable.savedselected)
    object Settings : BottomNavScreen("Settings",R.drawable.settings,R.drawable.settingsselected)

}