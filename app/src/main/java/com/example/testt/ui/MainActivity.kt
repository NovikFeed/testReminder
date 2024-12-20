package com.example.testt.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testt.ui.theme.TestTheme
import androidx.navigation.compose.rememberNavController
import android.Manifest
import android.util.Log
import android.widget.Toast
import com.example.testt.navigation.Screens
import com.example.testt.viewModels.ReminderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(
                this,
                "This app doesn't make sense without permissions to send notifications",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        setContent {
            TestTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val viewModel = hiltViewModel<ReminderViewModel>()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.rout) {
        composable(Screens.HomeScreen.rout) {
            HomeScreen(viewModel, navController)
        }
        composable(Screens.AddTaskScreen.rout) {
            AddTaskScreen(navController, viewModel)
        }
        composable("${Screens.EditTaskScreen.rout}/{reminderId}"){backStackEntry ->
            viewModel.getReminder(backStackEntry.arguments?.getString("reminderId")!!)
            AddTaskScreen(navController, viewModel, true)
        }
    }
}
