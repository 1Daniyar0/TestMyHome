package com.example.testmyhome.screens

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.testmyhome.ui.theme.SecondaryBackground
import com.example.testmyhome.ui.theme.TestMyHomeTheme
import com.example.testmyhome.ui.theme.Typography


@Composable
fun CamerasScreen(viewModel: MyHomeViewModel){

    val cameraResponse = viewModel.camerasLiveData.observeAsState()

    LaunchedEffect(Unit){
        viewModel.getCameras()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground)
            .padding(horizontal = 21.dp)
    ) {
       if (cameraResponse.value?.data?.cameras != null){
           ListOfCameras(cameraResponse.value?.data?.cameras!!)
       }
        else{
           ListOfCameras(listOf(Camera("","","",0,false,false)))
       }



    }
}

@Composable
fun ListOfCameras(list: List<Camera>){
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(11.dp),
        modifier = Modifier
            .padding(vertical = 8.dp)
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
    if (!item.room.isNullOrEmpty()){
        Text(
            text = item.room!!,
            style = Typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = SecondaryBackground
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxSize()

    ) {
        Box(){
            AsyncImage(
                model = item.snapshot,
                contentDescription ="Camera Preview",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize())
            if(item.rec!!){
                Image(painter = painterResource(id = R.drawable.rec),
                    contentDescription = "Recording Image",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart))
            }
            if(item.favorites!!){
                Image(painter = painterResource(id = R.drawable.star),
                    contentDescription = "Recording Image",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd))
            }
        }

        Text(
            text = item.name!!,
            style = Typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 18.dp))
    }



}

@Preview(showBackground = true)
@Composable
fun CamerasScreenPreview() {
    TestMyHomeTheme {
        val navController = rememberNavController()

    }
}