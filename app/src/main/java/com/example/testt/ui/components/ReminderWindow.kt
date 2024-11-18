package com.example.testt.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testt.R
import com.example.testt.domain.model.Reminder
import com.example.testt.navigation.Screens
import com.example.testt.viewModels.ReminderViewModel

@Composable
fun ReminderWindow(reminder: Reminder, viewModel: ReminderViewModel) {
    val style = TextStyle(
        fontFamily = FontFamily(Font(R.font.coolvetica)),
        fontSize = 24.sp,
        fontWeight = FontWeight.W400
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 9.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, top = 21.dp, bottom = 20.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = reminder.title, style = style)
                Text(text = "|${reminder.day}/${reminder.month}/${reminder.year}", fontSize = 13.sp, color = Color(0xFF646472))
                Icon(modifier = Modifier.graphicsLayer { rotationZ = 90f }.size(35.dp), imageVector = Icons.Default.MoreVert, contentDescription = "non")
            }
            Column {
                Text(text = "${reminder.hour}:${reminder.minute}", style = style)
                Row (modifier = Modifier.padding(top = 10.dp).fillMaxWidth(0.4f)){
                    Button(
                        onClick = {
                            viewModel.removeReminder(reminder.uniqueKey)
                        },
                        shape = CircleShape,
                        modifier = Modifier
                            .size(36.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF55D655),
                            contentColor = Color.White,

                            ),
                        border = BorderStroke(2.dp, Color.Black)

                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "add")
                    }
                    Button(
                        onClick = {
                            Log.d("cord", reminder.uniqueKey)
                                  viewModel.removeReminder(reminder.uniqueKey)
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(36.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6666),
                            contentColor = Color.White,

                            ),
                        border = BorderStroke(2.dp, Color.Black)

                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "cancel")
                    }
                }
            }
        }
//
    }
}