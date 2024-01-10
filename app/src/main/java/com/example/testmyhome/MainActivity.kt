package com.example.testmyhome

import android.content.res.Resources.Theme
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
import com.example.testmyhome.screens.TopBar
import com.example.testmyhome.ui.theme.TestMyHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestMyHomeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val navController = rememberNavController()

                        Text(text = getString(R.string.TopTitleText),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally))
                        TopBar(navController = navController)
                        MyNavGraph(navController = navController)
                    }

                }
            }
        }
    }
}

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "camera") {
        composable("camera") { CamerasScreen() }
        composable("door") { DoorsScreen() }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestMyHomeTheme {
        MainActivity()
    }
}