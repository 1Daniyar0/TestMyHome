package com.example.testmyhome


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmyhome.screens.CamerasScreen
import com.example.testmyhome.screens.DoorsScreen
import com.example.testmyhome.screens.MyHomeViewModel
import com.example.testmyhome.screens.TopBar
import com.example.testmyhome.ui.theme.PrimaryBackground
import com.example.testmyhome.ui.theme.TestMyHomeTheme
import com.example.testmyhome.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestMyHomeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel:MyHomeViewModel = koinViewModel()

                    Column {
                        Text(text = getString(R.string.TopTitleText),
                            style = Typography.bodyLarge,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 22.dp))
                        TopBar(navController = navController)
                        MyNavGraph(navController = navController,viewModel)
                    }

                }
            }
        }
    }
}

@Composable
fun MyNavGraph(navController: NavHostController,viewModel: MyHomeViewModel) {
    NavHost(navController = navController, startDestination = "camera") {
        composable("camera") { CamerasScreen(viewModel) }
        composable("door") { DoorsScreen(viewModel) }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestMyHomeTheme {
        MainActivity()
    }
}