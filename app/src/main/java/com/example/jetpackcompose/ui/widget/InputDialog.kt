package com.example.jetpackcompose.ui.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun InputDialog(
    url: String,
    onConfirmCLick: (String, String) -> Unit,
    onDismissClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { }
    ) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            ConstraintLayout {
                val (previewImage, input, confirmButton, dismissButton) = createRefs()
                ImageViewFromUrl(url = url, modifier = Modifier.constrainAs(previewImage) {
                    top.linkTo(parent.top, 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

                val editMessage = remember { mutableStateOf("") }
                val isError = remember { mutableStateOf(false) }
                InputText("Dogs name", Modifier.constrainAs(input) {
                    top.linkTo(previewImage.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(confirmButton.top,24.dp)
                }, editMessage, isError)

                PrimaryButton(text = "Done",
                    onClick = {
                        if (editMessage.value.isNotBlank()) {
                            isError.value = false
                            onConfirmCLick.invoke(url, editMessage.value)
                            // shouldShowDialog.value=false
                        } else {
                            isError.value = true
                        }
                    },
                    modifier = Modifier.constrainAs(confirmButton) {
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(dismissButton.start, 8.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                        width = Dimension.fillToConstraints
                    })
                SecondaryButton(text = "Dismiss",
                    onClick = {
                        onDismissClick()
                    },
                    modifier = Modifier.constrainAs(dismissButton) {
                        start.linkTo(confirmButton.end, 16.dp)
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                        width = Dimension.fillToConstraints
                    })
            }
        }
    }
}