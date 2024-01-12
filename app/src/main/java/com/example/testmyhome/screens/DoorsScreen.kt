package com.example.testmyhome.screens

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.domain.models.Camera
import com.example.domain.models.Door
import com.example.testmyhome.R
import com.example.testmyhome.ui.theme.ContentColor
import com.example.testmyhome.ui.theme.PrimaryBackground
import com.example.testmyhome.ui.theme.SecondaryBackground
import com.example.testmyhome.ui.theme.TestMyHomeTheme
import com.example.testmyhome.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel
import java.nio.file.WatchEvent
import kotlin.math.roundToInt


@Composable
fun DoorsScreen(viewModel: MyHomeViewModel){

    val doorResponse = viewModel.doorsLiveData.observeAsState()

    LaunchedEffect(Unit){
        viewModel.getDoors()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground)
            .padding(horizontal = 21.dp)
    ) {
        if (doorResponse.value != null){
            val doorsList = doorResponse.value
            ListOfDoors(
                list = doorsList!!,
                onFavoriteBtnClick = {
                    viewModel.updateDoorDb(it)
                },
                onConfirmDialogClick = {
                    viewModel.updateDoorDb(it)
                }
            ) 
        }
        else{

        }

    }
}

@Composable
fun ListOfDoors(list: List<Door>,onFavoriteBtnClick: (Door) -> Unit,onConfirmDialogClick:(Door) -> Unit ){
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(8.dp),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxSize()
            .background(Color.Transparent)

    ) {
        itemsIndexed(list){index, item ->
            DoorItem(list[index],onFavoriteBtnClick,onConfirmDialogClick)
        }
    }
}

@Composable
fun DoorItem(item: Door,onFavoriteBtnClick: (Door) -> Unit , onConfirmDialogClick:(Door) -> Unit){
    var moved by remember { mutableStateOf(false) }
    var dialogVisible by remember { mutableStateOf(false) }
    val pxToMove = with(LocalDensity.current) {
        -100.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(pxToMove, 0)
        } else {
            IntOffset.Zero
        },
        label = "offset"
    )
    if (dialogVisible){
        EditAlertDialog(
            item = item,
            onDismissRequest = {
                dialogVisible = !dialogVisible
                moved = !moved },
            onConfirmClick = {
                onConfirmDialogClick(it)
                dialogVisible = !dialogVisible
                moved = !moved}
        )
    }

    Box(
        modifier = Modifier.padding(bottom = 11.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ){
            IconButton(
                onClick = {
                    dialogVisible = !dialogVisible
                }
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.edit_button),
                    contentDescription = "Fav button"
                )
            }
            IconButton(
                onClick = {
                    val updateItem = item
                    updateItem.favorites = !item.favorites!!
                    onFavoriteBtnClick(updateItem)
                    moved = !moved
                }
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.star_in_circle),
                    contentDescription = "Fav button"
                )
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = SecondaryBackground
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
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
                    contentDescription ="Door Preview",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize())
                if(item.favorites!! && item.snapshot != "null"){
                    Image(painter = painterResource(id = R.drawable.star),
                        contentDescription = "Favorit Image",
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopEnd))
                }
            }
            Box(modifier = Modifier.fillMaxWidth()){
                Column (
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.CenterStart)
                ){
                    Text(
                        text = item.name!!,
                        style = Typography.bodyMedium)
                    if (!item.snapshot.isNullOrEmpty() && item.snapshot != "null"){
                        Text(
                            text = stringResource(R.string.Online),
                            style = Typography.bodySmall)
                    }
                }

                Image(painter = painterResource(id = R.drawable.lockon),
                    contentDescription = "Lock image",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(18.dp))

            }
        }

    }

}

@Composable
fun EditAlertDialog(item: Door,onDismissRequest: () -> Unit, onConfirmClick:(Door) -> Unit ){
    var text by remember { mutableStateOf(item.name) }
    val updateItem = item
    Dialog(
        onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(SecondaryBackground),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text(text = stringResource(R.string.name_door),
                style = Typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp))

            TextField(
                value = text!!,
                onValueChange = {
                    text = it},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(SecondaryBackground)


            )
            Row(){
                TextButton(onClick = {
                    updateItem.name = text
                    onConfirmClick(updateItem)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = "Сохранить",
                        style = Typography.bodyMedium,
                        color = ContentColor,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                TextButton(onClick = {
                    onDismissRequest()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)) {
                    Text(text = "Отмена",
                        style = Typography.bodyMedium,
                        color = ContentColor,
                        modifier = Modifier
                            .padding(16.dp)

                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DoorsScreenPreview() {
    TestMyHomeTheme {
        val navController = rememberNavController()

    }
}