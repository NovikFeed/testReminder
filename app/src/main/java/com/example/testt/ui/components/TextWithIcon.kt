package com.example.testt.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.io.encoding.Base64

@Composable
fun TextWithIcon(text : String, image : ImageVector){
    Row {
       Text(text = text, style = MaterialTheme.typography.labelMedium)
        Icon(modifier = Modifier.size(13.dp), imageVector = image, contentDescription = "текст")
    }
}
@Composable
fun TextWithIcon(text : String, image : Int){
    Row {
        Text(text = text, style = MaterialTheme.typography.labelMedium)
        Icon(modifier = Modifier.size(13.dp), painter = painterResource(id = image), contentDescription = "текст")
    }
}