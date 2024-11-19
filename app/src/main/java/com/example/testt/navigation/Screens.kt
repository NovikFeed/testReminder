package com.example.testt.navigation

sealed class Screens(val rout : String) {
    object HomeScreen : Screens("home")
    object AddTaskScreen : Screens("addTask")
    object EditTaskScreen : Screens("editTask")
}