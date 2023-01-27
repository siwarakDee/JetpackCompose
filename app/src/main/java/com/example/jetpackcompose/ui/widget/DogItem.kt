package com.example.jetpackcompose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DogItem(url:String,name:String,index:Int,onDeleteClick:(Int)->Unit){
    ConstraintLayout {
        val (image,buttonDelete,textName)= createRefs()
        ImageViewFromUrl(url = url, modifier = Modifier.constrainAs(image){
            top.linkTo(parent.top,16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(textName.top,8.dp)
        })

        TextTitle(text = name, modifier = Modifier.constrainAs(textName){
            bottom.linkTo(parent.bottom,16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        TextBody(text = "X", color = Color.White,
            Modifier.background(Color.Red, RoundedCornerShape(4.dp)).padding(horizontal = 8.dp).constrainAs(buttonDelete) {
                top.linkTo(parent.top,8.dp)
                end.linkTo(parent.end,8.dp)
            }.clickable {
                onDeleteClick.invoke(index)
            })
    }
}