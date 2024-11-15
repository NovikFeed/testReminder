package com.example.testt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testt.ui.theme.TestTheme
import androidx.navigation.compose.rememberNavController
import com.example.testt.navigation.Screens
import com.example.testt.viewModels.ReminderViewModel
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
    val viewModel = hiltViewModel<ReminderViewModel>()
    val reminders = viewModel.remindersList.collectAsState().value
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.rout ){
        composable(Screens.HomeScreen.rout){
            HomeScreen(reminders = reminders, navController)
        }
        composable(Screens.AddTaskScreen.rout){
            AddTaskScreen(navController, viewModel)
        }
    }
}