package com.example.smsrly.presentation.ui.screens.sell.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.components.AddTextField
import com.example.smsrly.presentation.ui.screens.components.CustomButton
import com.example.smsrly.presentation.ui.screens.components.CustomLoadingIndicator
import com.example.smsrly.presentation.ui.screens.components.LocationBox
import com.example.smsrly.presentation.ui.screens.components.SmallSizedTextField
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.events.SellEvents
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.states.SellState
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.SellViewModel
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun AddsBody(
    modifier: Modifier,
    navController: NavController,
    viewModel: SellViewModel
) {
    val state = viewModel.state.collectAsState()
    val title = rememberSaveable { mutableStateOf("") }
    val price = rememberSaveable { mutableStateOf("") }
    val desc = rememberSaveable { mutableStateOf("") }
    val floors = rememberSaveable { mutableStateOf("") }
    val rooms = rememberSaveable { mutableStateOf("") }
    val bathRoom = rememberSaveable { mutableStateOf("") }
    val area = rememberSaveable { mutableStateOf("") }




    LaunchedEffect(Unit) {
        viewModel.events.collect {events ->
            when(events){
                is SellEvents.ShowToast->{
                    val message = events.message
                    Toast.makeText(navController.context,message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    LaunchedEffect(state.value) {
        if(state.value is SellState.Success){
            title.value = ""
            price.value =""
            desc.value = ""
            floors.value = ""
            rooms.value =""
            bathRoom.value =""
            area.value=""
            viewModel.reset()
        }
    }





    AddTextField("Title", title.value, { newValue ->
        title.value = newValue
    },KeyboardType.Text)
    Spacer(Modifier.height(20.dp))
    AddTextField("Price", price.value, { newValue ->
        price.value = newValue
    }, KeyboardType.Number)
    Spacer(Modifier.height(20.dp))
    AddTextField("Description", desc.value, { newValue ->
        desc.value = newValue
    },KeyboardType.Text)
    Spacer(Modifier.height(20.dp))
    LocationBox(navController, viewModel)
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SmallSizedTextField(
            "Floors",
            modifier = Modifier.weight(1f),
            floors.value,
            { newValue ->
                floors.value = newValue
            },KeyboardType.Number)
        SmallSizedTextField("Rooms", modifier = Modifier.weight(1f), rooms.value, { newValue ->
            rooms.value = newValue
        },KeyboardType.Number)

    }
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SmallSizedTextField(
            "Bathroom",
            modifier = Modifier.weight(1f),
            bathRoom.value,
            { newValue ->
                bathRoom.value = newValue
            },KeyboardType.Number)
        SmallSizedTextField("Area", modifier = Modifier.weight(1f),area.value,{newValue->
            area.value = newValue
        },KeyboardType.Number)

    }
    Spacer(Modifier.height(20.dp))
    if(state.value == SellState.Loading){

        CustomLoadingIndicator()
    }else{
        CustomButton( "Submit", {
            viewModel.uploadARealState(
                title.value,
                desc.value,
                price.value,
                floors.value,
                rooms.value,
                bathRoom.value,
                area.value
            )
        }, Primary,true)
    }
}