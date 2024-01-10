package com.example.testmyhome.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testmyhome.R
import com.example.testmyhome.ui.theme.ContentColor
import com.example.testmyhome.ui.theme.TestMyHomeTheme
import com.example.testmyhome.ui.theme.Typography


@Composable
fun TopBar(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    Row(modifier = Modifier
        .height(44.dp)
        .fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)


            ) {
                TextButton(
                    onClick = {
                        if (route != "camera"){
                            navController.navigate("camera") }
                    },
                    modifier = Modifier
                        .fillMaxSize()

                )
                {
                    Text(
                        text = stringResource(id = R.string.CamerasTitle),
                        style = Typography.bodyMedium,
                        color = Color.Black
                    )

                }
                Divider(
                    color = if(route == "camera") {
                        MaterialTheme.colorScheme.primary
                    }
                    else {
                        Color.LightGray },
                    thickness = 2.dp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)

            ) {
                TextButton(
                    onClick = {
                        if (route != "door"){
                            navController.navigate("door") }
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                )

                {
                    Text(
                        text = stringResource(id = R.string.DoorsTitle),
                        style = Typography.bodyMedium,
                        color = Color.Black)

                }
                Divider(
                    color = if(route == "door") {
                        ContentColor
                    }
                    else {
                        Color.LightGray },
                    thickness = 2.dp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter))

            }
        }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TestMyHomeTheme {
        val navController = rememberNavController()
        TopBar(navController)
    }
}