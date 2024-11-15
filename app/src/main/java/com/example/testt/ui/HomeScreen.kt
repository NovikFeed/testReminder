package com.example.testt.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.testt.R
import com.example.testt.domain.model.Reminder
import com.example.testt.navigation.Screens
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(reminders: List<Reminder>, navController: NavHostController) {
    if (reminders.isNotEmpty()) {

    } else {
        EmptyListScreen(navController)
    }
}

@Composable
fun EmptyListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.09f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 51.dp),
                    fontSize = 32.sp,
                    text = "TestReminder",
                    fontFamily = FontFamily(Font(R.font.coolvetica))
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.09f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screens.AddTaskScreen.rout) },
                    modifier = Modifier.size(39.dp).padding(end = 0.dp).zIndex(2f),
                    containerColor = Color(0xFF007AFF),
                    contentColor = Color.White
                ){
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = Color(0xFF9DCCFF),
                        disabledContentColor = Color(0xFF005CBB)

                    ),
                    border = BorderStroke(1.dp, Color(0xFF007AFF)),
                    modifier = Modifier
                        .size(74.dp, 18.dp)
                        .padding(start = 0.dp)
                        .offset(x = -3.dp)
                        .zIndex(1f),
                    enabled = false,
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(0.dp),
                    content = {
                        Text(
                            text = "Add Task",
                            fontFamily = FontFamily(Font(R.font.coolvetica)),
                            fontSize = 11.sp,
                        )
                    }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "background"
                )
            }
        }
    )
}