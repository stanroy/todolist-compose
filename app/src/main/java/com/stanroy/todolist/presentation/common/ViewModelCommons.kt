package com.stanroy.todolist.presentation.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object ViewModelCommons {

    val dbScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

}