package com.example.testt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testt.ui.theme.TestTheme
import kotlinx.coroutines.flow.flow
import androidx.navigation.compose.rememberNavController
import com.example.testt.domain.model.Reminder
import com.example.testt.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                MainContent()
            }
        }
    }
}
@Composable
fun MainContent(){
    val reminders = flow{emit(emptyList<Reminder>())}
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.rout ){
        composable(Screens.HomeScreen.rout){
            HomeScreen(reminders = reminders, navController)
        }
        composable(Screens.AddTaskScreen.rout){
            AddTaskScreen(navController)
        }
    }
}