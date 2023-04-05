package com.stanroy.todolist.presentation.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stanroy.todolist.R
import com.stanroy.todolist.presentation.theme.Shapes

/**
 * Simple rounded checkbox
 *
 *
 * @param state checkbox state (checked, unchecked)
 * @param size checkbox size (width, height)
 * @param checkMarkColorFilter default checkmark color is White
 * @param checkedBackground background when checkbox is checked
 * @param unCheckedBackground background when checkbox is unchecked
 * @param onStateChanged callback invoked when checkbox is clicked, it passes current checkbox state
 */
@Composable
fun RoundedCheckBox(
    state: Boolean = false,
    size: Dp = 20.dp,
    checkMarkColorFilter: ColorFilter? = null,
    checkedBackground: Color = Color.Green,
    unCheckedBackground: Color = Color.White,
    onStateChanged: (state: Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(state) }

    val animatedBackgroundColor by animateColorAsState(targetValue = if (checked) checkedBackground else unCheckedBackground)
    Box(
        modifier = Modifier
            .size(size)
            .clip(shape = Shapes.small)
            .border(width = 2.5.dp, color = Color.Gray, shape = Shapes.small)
            .background(color = animatedBackgroundColor)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                checked = !checked
                onStateChanged(checked)
            }
    ) {
        Image(modifier = Modifier.padding(2.dp), painter = painterResource(id = R.drawable.checkmark), contentDescription = "check", colorFilter = checkMarkColorFilter)
    }
}

