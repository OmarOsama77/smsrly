import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.myadds.screens.requested.Requested
import com.example.smsrly.presentation.ui.screens.myadds.screens.saved.Saved
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.Uploaded
import com.example.smsrly.presentation.ui.theme.Primary
import kotlinx.serialization.Serializable

@Serializable
data object MyAddsRoute
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAdds(navController: NavController) {
    val screens = listOf("Uploaded", "Requested", "Saved")
    var selectedTab by rememberSaveable{mutableIntStateOf(0)}


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Adds", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth(),
                containerColor = Primary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab])
                            .height(3.dp)
                            .background(Color.White),
                        color = Color.Transparent
                    )
                }
            ) {
                screens.forEachIndexed { index, screenName ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(screenName, color = Color.White) }
                    )
                }
            }

            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                when (selectedTab) {
                    0 -> Uploaded(navController)
                    1 -> Requested(navController)
                    2 -> Saved(navController)
                }
            }
        }
    }
}

