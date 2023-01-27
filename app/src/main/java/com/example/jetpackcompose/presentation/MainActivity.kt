package com.example.jetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.ui.widget.DialogBoxLoading
import com.example.jetpackcompose.ui.widget.ImageViewFromUrl
import com.example.jetpackcompose.ui.widget.PrimaryButton
import com.example.jetpackcompose.ui.widget.TitleBar

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
                        items(items = favourites, itemContent = { item ->
                            ImageViewFromUrl(item.second)
                        })
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

                image?.let {
                    if (recentUrl.value!=it){
                        recentUrl.value = it
                        favourites.add(Pair("name",it))
                    }
                }
                if (openDialog) {
                    DialogBoxLoading()
                }

            }
        }
    }
}

