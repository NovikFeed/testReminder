package com.example.testt.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testt.R
import com.example.testt.domain.model.Reminder
import com.example.testt.navigation.Screens
import com.example.testt.viewModels.ReminderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderWindow(
    reminder: Reminder,
    viewModel: ReminderViewModel,
    navController: NavHostController
) {
    var expanded by remember { mutableStateOf(false) }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, top = 21.dp, bottom = 20.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = reminder.title, style = style)
                Text(
                    text = "|${if (reminder.day in 1..9) "0${reminder.day}" else reminder.day}/${if (reminder.month in 1..9) "0${reminder.month}" else reminder.month}/${reminder.year}",
                    fontSize = 13.sp,
                    color = Color(0xFF646472)
                )
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                    expanded = !expanded
                    viewModel.setBlur()

                }) {
                    Icon(
                        modifier = Modifier
                            .graphicsLayer { rotationZ = 90f }
                            .size(35.dp)
                            .menuAnchor(),
                        imageVector = Icons.Default.MoreVert, contentDescription = "non")
                    ExposedDropdownMenu(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFF0F0F0), shape = RoundedCornerShape(20.dp)
                            )
                            .size(width = 90.dp, 70.dp)
                            .border(1.dp, Color.Black),
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                            viewModel.setBlur()
                        },
                    ) {
                        DropdownMenuItem(
                            modifier = Modifier
                                .padding(0.dp)
                                .height(30.dp)
                                .background(Color.Transparent),
                            onClick = {
                                expanded = false
                                viewModel.setBlur()
                                navController.navigate("${Screens.EditTaskScreen.rout}/${reminder.uniqueKey}")
                            },
                            text = {
                                Text(
                                    text = "Edit...",
                                    modifier = Modifier.padding(0.dp),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 15.sp, color = Color.Black
                                    )
                                )
                            },
                        )
                        DropdownMenuItem(
                            modifier = Modifier
                                .padding(0.dp)
                                .height(30.dp)
                                .background(Color.Transparent)
                                .border(1.dp, Color.Black),
                            onClick = {
                                expanded = false
                                viewModel.setBlur()
                                viewModel.removeReminder(reminder.uniqueKey)
                            },
                            text = {
                                Text(
                                    text = "Delete...",
                                    modifier = Modifier.padding(0.dp),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 15.sp, color = Color.Red
                                    )
                                )
                            },
                        )

                    }
                }
            }
            Column {
                Text(
                    text = "${if (reminder.hour in 0..9) "0${reminder.hour}" else reminder.hour}:${if (reminder.minute in 0..9) "0${reminder.minute}" else reminder.minute}",
                    style = style
                )
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.4f)
                ) {
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