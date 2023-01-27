package com.example.jetpackcompose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.EarthToneGreen


@Composable
fun TextTitle(text:String,color: Color?=null,modifier: Modifier){
    Text(text = text, modifier = Modifier.padding(4.dp).then(modifier), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = color?:Color.Black)
}

@Composable
fun TextBody(text:String,color: Color?=null,modifier: Modifier){
    Text(text = text, modifier = Modifier.padding(4.dp).then(modifier), fontSize = 18.sp,  fontWeight = FontWeight.Normal, color = color?:Color.Gray)
}

@Composable
fun TextDescription(text:String,color: Color?=null,modifier: Modifier){
    Text(text = text, modifier = Modifier.padding(4.dp).then(modifier), fontSize = 14.sp,  fontWeight = FontWeight.Normal, color = color?:Color.Gray)
}

@Composable
fun TitleBar(title:String,modifier: Modifier){
        Box(Modifier.background(EarthToneGreen).fillMaxWidth().then(modifier)
            .padding(top = 12.dp, bottom = 12.dp), contentAlignment = Alignment.Center) {
            TextTitle(text =title,color = Color.White,Modifier)
        }
}
@Preview
@Composable
fun TitlePreview(){
    TextTitle("Red text title",Color.Black,Modifier)
}

@Preview
@Composable
fun BodyPreview(){
    TextBody("Red text title",Color.Black,Modifier)
}

@Preview
@Composable
fun DescriptionPreview(){
    TextDescription("Red text title",Color.Black,Modifier)
}

@Preview
@Composable
fun TitleBarPreview(){
    TitleBar("example",Modifier)
}

