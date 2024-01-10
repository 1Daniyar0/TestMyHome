package com.example.testmyhome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.domain.models.Camera
import com.example.domain.models.Door
import com.example.testmyhome.ui.theme.PrimaryBackground
import com.example.testmyhome.ui.theme.TestMyHomeTheme


@Composable
fun DoorsScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground)
            .padding(horizontal = 21.dp)
    ) {
        val list = listOf(
            Door("Door 1","https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png","FIRST",1,true),
            Door("Door 1","https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png","FIRST",1,true),
            Door("Door 1","https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png","FIRST",1,true)
        )
        ListOfDoors(list)
    }
}

@Composable
fun ListOfDoors(list: List<Door>){
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(11.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)

    ) {
        itemsIndexed(list){index, item ->
            DoorItem(list[index])
        }
    }
}

@Composable
fun DoorItem(item: Door){
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
fun DoorsScreenPreview() {
    TestMyHomeTheme {
        val navController = rememberNavController()
        DoorsScreen()
    }
}