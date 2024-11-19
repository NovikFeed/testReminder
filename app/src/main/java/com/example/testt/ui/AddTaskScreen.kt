package com.example.testt.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.testt.R
import com.example.testt.domain.model.Reminder
import com.example.testt.navigation.Screens
import com.example.testt.ui.components.TextWithIcon
import com.example.testt.ui.features.bottomBorder
import com.example.testt.viewModels.ReminderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavHostController,
    viewModel: ReminderViewModel,
    editing: Boolean = false
) {
    val reminder by viewModel.reminderByKey.collectAsState()
    Log.d("cord", reminder.toString())
    val title = remember { mutableStateOf("") }
    val hour = remember { mutableStateOf("") }
    val minute = remember { mutableStateOf("") }
    val day = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val year = remember { mutableStateOf("") }
    val options = listOf("Once", "Daily", "Mon to Fri")
    var expanded by remember { mutableStateOf(false) }
    var repeat by remember { mutableStateOf(options[0]) }
    if (editing) {
        title.value = reminder.title
        hour.value = reminder.hour.toString()
        minute.value = reminder.minute.toString()
        day.value = reminder.day.toString()
        month.value = reminder.month.toString()
        year.value = reminder.year.toString()
        repeat = reminder.repeat
    }

    Scaffold(topBar = {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(93.dp)
                    .padding(top = 3.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screens.HomeScreen.rout)
                    },
                    shape = CircleShape,
                    modifier = Modifier.size(38.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6666),
                        contentColor = Color.White,

                        ),
                    border = BorderStroke(2.dp, Color.Black)

                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "cancel")
                }
                Text(
                    fontSize = 32.sp,
                    text = "TestReminder",
                    fontFamily = FontFamily(Font(R.font.coolvetica))
                )
                Button(
                    onClick = {
                        if (editing) {
                            viewModel.updateReminder(
                                reminder.uniqueKey,
                                title.value,
                                hour.value.toInt(),
                                minute.value.toInt(),
                                day.value.toInt(),
                                month.value.toInt(),
                                year.value.toInt(),
                                repeat
                            )
                        } else {
                            viewModel.upsertReminders(
                                title.value,
                                hour.value.toInt(),
                                minute.value.toInt(),
                                day.value.toInt(),
                                month.value.toInt(),
                                year.value.toInt(),
                                repeat
                            )
                        }

                        title.value = ""
                        hour.value = ""
                        minute.value = ""
                        day.value = ""
                        month.value = ""
                        year.value = ""
                        repeat = options[0]
                    },
                    enabled = (title.value != "" && hour.value != "" && minute.value != "" && day.value != "" && month.value != "" && year.value != ""),
                    shape = CircleShape,
                    modifier = Modifier.size(38.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF55D655),
                        contentColor = Color.White,

                        ),
                    border = BorderStroke(2.dp, Color.Black)

                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "add")
                }

            }
        }

    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .offset(y = 22.dp)
                    .zIndex(1f),
                enabled = false,
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color(0xFF0080FF),
                    disabledContentColor = Color.White
                ),
                contentPadding = PaddingValues(26.dp, 6.dp)
            ) {
                Text(
                    text = "Make your own Reminder.",
                    fontFamily = FontFamily(Font(R.font.alte_haas_grotesk)),
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 39.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(modifier = Modifier.padding(start = 49.dp, top = 41.dp)) {
                    TextWithIcon(text = "Title", image = Icons.Outlined.Create)
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BasicTextField(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 13.dp, bottom = 6.dp)
                            .bottomBorder(1.dp, Color.Black, widthEnd = 620f),
                            singleLine = true,
                            value = title.value,
                            onValueChange = { title.value = it },
                            textStyle = TextStyle(
                                color = Color.Gray,
                                fontSize = 15.sp,

                                ),
                            decorationBox = { innerTextField ->

                                if (title.value.isEmpty()) {
                                    Text(
                                        text = "Insert title",
                                        color = Color.Gray,
                                        fontSize = 15.sp
                                    )
                                }
                                innerTextField()
                            })
                    }
                    Row {
                        Column(modifier = Modifier.padding(top = 23.dp)) {
                            TextWithIcon(text = "Time", image = R.drawable.clock)
                            Row(modifier = Modifier.padding(top = 6.dp)) {
                                BasicTextField(
                                    value = hour.value,
                                    onValueChange = {
                                        if (it == "") {
                                            hour.value = it
                                        } else if (it != "," && it != ".") {
                                            if (it.toInt() in 0..23) hour.value = it
                                        }
                                    },
                                    modifier = Modifier
                                        .width(40.dp)
                                        .bottomBorder(1.dp, Color.Black, 70f),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    decorationBox = { innerTextField ->

                                        if (hour.value.isEmpty()) {
                                            Text(
                                                text = "00",
                                                color = Color.Gray,
                                                style = MaterialTheme.typography.titleSmall,
                                            )
                                        }
                                        innerTextField()
                                    },
                                    textStyle = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = ":",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(0.dp)
                                )
                                BasicTextField(
                                    value = minute.value,
                                    onValueChange = {
                                        if (it == "") {
                                            minute.value = it
                                        } else if (it != "," && it != ".") {
                                            if (it.toInt() in 0..59) minute.value = it
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(start = 9.dp)
                                        .width(40.dp)
                                        .bottomBorder(1.dp, Color.Black, 90f, 20f),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    decorationBox = { innerTextField ->

                                        if (minute.value.isEmpty()) {
                                            Text(
                                                textAlign = TextAlign.Center,
                                                text = "00",
                                                color = Color.Gray,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                        innerTextField()
                                    },
                                    textStyle = MaterialTheme.typography.titleSmall.copy(
                                        textAlign = TextAlign.Center
                                    )
                                )

                            }
                        }
                        Column(modifier = Modifier.padding(top = 23.dp, start = 20.dp)) {
                            TextWithIcon(text = "Calendar", image = Icons.Outlined.DateRange)
                            Row(modifier = Modifier.padding(top = 6.dp)) {
                                BasicTextField(
                                    value = day.value,
                                    onValueChange = {
                                        if (it == "") {
                                            day.value = it
                                        } else if (it != "," && it != ".") {
                                            if (it.toInt() in 0..30) day.value = it
                                        }
                                    },
                                    modifier = Modifier
                                        .width(40.dp)
                                        .bottomBorder(1.dp, Color.Black, 70f)
                                        .padding(end = 0.dp),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    decorationBox = { innerTextField ->

                                        if (day.value.isEmpty()) {
                                            Text(
                                                text = "dd",
                                                color = Color.Gray,
                                                style = MaterialTheme.typography.titleSmall,
                                            )
                                        }
                                        innerTextField()
                                    },
                                    textStyle = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = ":",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(0.dp)
                                )
                                BasicTextField(
                                    value = month.value,
                                    onValueChange = {
                                        if (it == "") {
                                            month.value = it
                                        } else if (it != "," && it != ".") {
                                            if (it.toInt() in 1..12) month.value = it
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(start = 6.dp)
                                        .width(40.dp)
                                        .bottomBorder(1.dp, Color.Black, 90f, 0f),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    decorationBox = { innerTextField ->

                                        if (month.value.isEmpty()) {
                                            Text(
                                                textAlign = TextAlign.Center,
                                                text = "mm",
                                                color = Color.Gray,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                        innerTextField()
                                    },
                                    textStyle = MaterialTheme.typography.titleSmall.copy(
                                        textAlign = TextAlign.Center
                                    )
                                )
                                Text(
                                    text = ":",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(0.dp)
                                )
                                BasicTextField(
                                    value = year.value,
                                    onValueChange = {
                                        if (it == "") {
                                            year.value = it
                                        } else if (it != "," && it != ".") {
                                            if (it.toInt() in 1..2030) year.value = it
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(start = 6.dp)
                                        .width(50.dp)
                                        .bottomBorder(1.dp, Color.Black, 110f, 0f),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    decorationBox = { innerTextField ->

                                        if (year.value.isEmpty()) {
                                            Text(
                                                textAlign = TextAlign.Center,
                                                text = "yyyy",
                                                color = Color.Gray,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                        innerTextField()
                                    },
                                    textStyle = MaterialTheme.typography.titleSmall
                                )
                            }
                        }


                    }
                    Column(modifier = Modifier.padding(top = 23.dp, bottom = 20.dp)) {
                        TextWithIcon(text = "Repeat", image = R.drawable.repeat)
                        ExposedDropdownMenuBox(modifier = Modifier
                            .padding(top = 18.dp, end = 39.dp)
                            .fillMaxWidth()
                            .height(35.dp), expanded = expanded, onExpandedChange = {
                            expanded = !expanded
                        }) {
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.menuAnchor(),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.Gray,
                                    containerColor = Color(0xFFF0F0F0)
                                )

                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = repeat)
                                    Image(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Arrow",
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }

                            }
                            ExposedDropdownMenu(
                                modifier = Modifier.background(
                                    color = Color(0xFFF0F0F0), shape = RoundedCornerShape(20.dp)
                                ),
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                },
                            ) {
                                options.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        modifier = Modifier
                                            .padding(0.dp)
                                            .height(22.dp)
                                            .background(Color.Transparent),
                                        onClick = {
                                            repeat = selectionOption
                                            expanded = false
                                        },
                                        text = {
                                            Text(
                                                text = selectionOption,
                                                modifier = Modifier.padding(0.dp),
                                                style = MaterialTheme.typography.titleSmall.copy(
                                                    fontSize = 15.sp, color = Color.Gray
                                                )
                                            )
                                        },
                                    )
                                }
                            }
                        }

                    }
                }

            }
        }
    })
}
