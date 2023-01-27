package com.example.jetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.ui.widget.*

class MainActivity : ComponentActivity() {

    private val viewModel :MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            JetpackComposeTheme {
                val favourites = remember { mutableStateListOf<Pair<String,String>>()}
                val image by viewModel.dogImage.observeAsState(null)
                val openDialog by viewModel.loading.observeAsState(false)
                val recentUrl = remember { mutableStateOf<String?>(null) }
                val shouldShowDialog = remember { mutableStateOf(false) }
                ConstraintLayout(modifier = Modifier.fillMaxSize()){
                    val (titleBar, searchButton, imageList) = createRefs()

                    TitleBar("Random your dogs", modifier = Modifier.constrainAs(titleBar)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                    val listState = rememberLazyListState()
                    LaunchedEffect(favourites.size) {
                        listState.animateScrollToItem(favourites.size)
                    }

                    LazyColumn(state = listState,modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(imageList) {
                            top.linkTo(titleBar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(searchButton.top)
                            height = Dimension.fillToConstraints
                        }) {
                        itemsIndexed(items = favourites){index, item ->
                            DogItem(url = item.second, name = item.first, index = index, onDeleteClick = {
                                favourites.removeAt(index)
                            })
                        }
//                        items(items = favourites, itemContent = { item ->
//                            ImageViewFromUrl(item.second,Modifier)
//                        })
                    }

                    PrimaryButton(text = "Random", onClick = {viewModel.fetchDog()},
                    modifier = Modifier
                        .constrainAs(searchButton) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom, 16.dp)
                        }
                        .wrapContentHeight()
                        .wrapContentWidth())
                }

                if (openDialog) {
                    DialogBoxLoading()
                }
                if (shouldShowDialog.value){
                    InputDialog(recentUrl.value?:"", onConfirmCLick = {url,name->
                        favourites.add(Pair(name,url))
                        shouldShowDialog.value=false
                    }, onDismissClick = {
                        shouldShowDialog.value=false
                    })
                }

                image?.let {imageUrl->
                    if (recentUrl.value!=imageUrl){
                        recentUrl.value = imageUrl
                        shouldShowDialog.value =true
                    }
                }
            }
        }
    }
}

