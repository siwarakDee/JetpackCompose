package com.example.jetpackcompose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputText(
    hint: String,
    modifier: Modifier,
    editMessage: MutableState<String>,
    isError: MutableState<Boolean>
){
    TextField(
        value = editMessage.value,
        onValueChange = { message -> editMessage.value = message },
        singleLine = true,
        label = {TextDescription(text = hint, modifier = Modifier)} ,
        isError=isError.value,
        modifier = modifier.then(
            Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                .padding(4.dp))
    )
}


@Preview
@Composable
fun previewInput(){
    val editMessage = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(false) }
    InputText("", Modifier, editMessage, error)
}

@Preview
@Composable
fun previewErrorInput(){
    val editMessage = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(true) }
    InputText("", Modifier, editMessage, error)
}