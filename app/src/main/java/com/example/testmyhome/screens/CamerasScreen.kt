package com.example.testmyhome.screens

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.domain.models.Camera
import com.example.testmyhome.R
import com.example.testmyhome.ui.theme.PrimaryBackground
import com.example.testmyhome.ui.theme.TestMyHomeTheme



@Composable
fun CamerasScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground)
            .padding(horizontal = 21.dp)
    ) {

        val list = listOf(
            Camera("Camera 1","https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png","FIRST",1,true,false),
            Camera("Camera 1","https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png","FIRST",1,true,false),
            Camera("Camera 1","https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png","FIRST",1,true,false)
        )
        Text(
            text = "Гостиная",
            fontSize = 21.sp,
            modifier = Modifier
                .padding(vertical = 21.dp)
            )

        ListOfCameras(list)
    }
}

@Composable
fun ListOfCameras(list: List<Camera>){
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(11.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)

    ) {
        items(list.size - 1){
            CameraItem(list[it])
        }
    }
}





@Composable
fun CameraItem(item: Camera){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxSize()

    ) {
        AsyncImage(
            model = item.snapshot,
            contentDescription ="Camera Preview",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize())
        Text(
            text = item.name,
            fontSize = 17.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 18.dp))
    }



}

@Preview(showBackground = true)
@Composable
fun CamerasScreenPreview() {
    TestMyHomeTheme {
        val navController = rememberNavController()
        CamerasScreen()
    }
}