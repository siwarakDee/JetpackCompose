package com.example.jetpackcompose.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun ImageViewFromUrl(url: String,modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        contentScale= ContentScale.Crop,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .fillMaxWidth()
            .aspectRatio(1.0f).border(2.dp, Color.Gray)
            .then(modifier)
    )
}