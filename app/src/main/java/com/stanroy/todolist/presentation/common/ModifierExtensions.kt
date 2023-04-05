package com.stanroy.todolist.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


val windowVerticalPadding = 32.dp
val windowHorizontalPadding = 24.dp

val Modifier.defaultWindowPadding: Modifier
    get() = this.padding(vertical = windowVerticalPadding, horizontal = windowHorizontalPadding)