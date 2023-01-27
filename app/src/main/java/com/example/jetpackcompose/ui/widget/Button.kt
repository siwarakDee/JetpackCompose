package com.example.jetpackcompose.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.EarthToneBlue

@Composable
fun PrimaryButton(text:String,onClick:()->Unit,modifier: Modifier){
    Button(onClick = onClick,
        shape= RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = EarthToneBlue,
            disabledBackgroundColor= Color.LightGray),
        border = BorderStroke(1.dp,Color.Cyan),
        modifier = modifier
    ){
        TextBody(text = text,color = Color.White,Modifier)
    }
}

@Preview
@Composable
fun PreviewPrimaryButton(){
    PrimaryButton(text = "Random", modifier = Modifier, onClick = {

    })
}