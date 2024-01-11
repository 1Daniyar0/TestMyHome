package com.example.testmyhome.screens

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.domain.models.Camera
import com.example.domain.models.Door
import com.example.testmyhome.R
import com.example.testmyhome.ui.theme.PrimaryBackground
import com.example.testmyhome.ui.theme.SecondaryBackground
import com.example.testmyhome.ui.theme.TestMyHomeTheme
import com.example.testmyhome.ui.theme.Typography

import kotlin.math.roundToInt


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
       if (cameraResponse.value != null){
           val cameraListDb = cameraResponse.value
           ListOfCameras(cameraListDb!!){
               viewModel.updateCameraDb(it)
           }
       }
        else{

       }
    }
}

@Composable
fun ListOfCameras(list: List<Camera>,onFavoriteBtnClick: (Camera) -> Unit ){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)

    ) {
        itemsIndexed(list){index, item ->
            CameraItem(item,onFavoriteBtnClick)
        }
    }
}

@Composable
fun CameraItem(item: Camera,onFavoriteBtnClick: (Camera) -> Unit ){
    var moved by remember { mutableStateOf(false) }
    val pxToMove = with(LocalDensity.current) {
        -50.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(pxToMove, 0)
        } else {
            IntOffset.Zero
        },
        label = "offset"
    )

    if (item.room != null && item.room != "null"){
        Text(
            text = item.room!!,
            style = Typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
    }
    Box(
        modifier = Modifier.padding(bottom = 11.dp)
    ) {
        IconButton(
            onClick = {
                val updateItem = item
                updateItem.favorites = !item.favorites!!
                onFavoriteBtnClick(updateItem)
                moved = !moved
                      },
            modifier = Modifier.align(Alignment.CenterEnd))
        {
            Image(
                painter = painterResource(id = R.drawable.star_in_circle),
                contentDescription = "Fav button")
        }



        Card(
            colors = CardDefaults.cardColors(
                containerColor = SecondaryBackground
            ),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .offset {
                    offset
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    moved = !moved
                }



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
                        contentDescription = "Favorit Image",
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

}

@Preview(showBackground = true)
@Composable
fun CamerasScreenPreview() {
    TestMyHomeTheme {
        val navController = rememberNavController()

    }
}