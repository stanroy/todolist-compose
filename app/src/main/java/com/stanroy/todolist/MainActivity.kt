package com.stanroy.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stanroy.todolist.presentation.common.Screen
import com.stanroy.todolist.presentation.common.addEditTaskRoute
import com.stanroy.todolist.presentation.common.taskId
import com.stanroy.todolist.presentation.screen_add_task.AddTaskScreen
import com.stanroy.todolist.presentation.screen_task_list.ListScreen
import com.stanroy.todolist.presentation.theme.TodolistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
//        lifecycleScope.launch {
//            // fix for issue with xiaomi phones not showing the compose layout correctly -> https://issuetracker.google.com/issues/227926002
//            delay(50)
//            window.setBackgroundDrawableResource(android.R.color.transparent)
//        }
        setContent {
            TodolistTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold {
                        NavHost(
                            modifier = Modifier.padding(it.calculateBottomPadding()),
                            navController = navController,
                            startDestination = Screen.ListScreen.route
                        ) {
                            composable(Screen.ListScreen.route) {
                                ListScreen(navController)
                            }

                            composable(
                                Screen.AddTaskScreen.addEditTaskRoute(),
                                arguments = listOf(navArgument(Screen.AddTaskScreen.taskId) {
                                    defaultValue = -1
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->
                                AddTaskScreen(
                                    navController = navController,
                                    taskIdToEdit = backStackEntry.arguments?.getInt(Screen.AddTaskScreen.taskId)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}