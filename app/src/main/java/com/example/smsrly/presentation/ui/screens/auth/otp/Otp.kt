import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.auth.otp.components.OtpBottom
import com.example.smsrly.presentation.ui.screens.auth.otp.components.OtpForm
import com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel.OtpViewModel
import kotlinx.serialization.Serializable

@Serializable
data object OtpRoute

@Composable
fun Otp(navController: NavController) {

    val scrollState = rememberScrollState()
    val viewModel : OtpViewModel = hiltViewModel()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) viewModel.fetchLocation()
    }
    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    Column(
        modifier = Modifier
            .imePadding()
            .verticalScroll(scrollState)
            .padding(top = 45.dp, start = 25.dp, end = 25.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {


        OtpForm(viewModel,navController)
        OtpBottom({
            navController.popBackStack()
        })

    }
}


