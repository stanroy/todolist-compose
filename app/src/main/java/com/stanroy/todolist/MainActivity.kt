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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stanroy.todolist.presentation.common.Screen
import com.stanroy.todolist.presentation.screen_add_task.AddTaskScreen
import com.stanroy.todolist.presentation.screen_task_list.ListScreen
import com.stanroy.todolist.presentation.theme.TodolistTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            // fix for issue with xiaomi phones not showing the compose layout correctly -> https://issuetracker.google.com/issues/227926002
            delay(50)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
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

                            composable(Screen.AddTaskScreen.route) {
                                AddTaskScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodolistTheme {
        Greeting("Android")
    }
}