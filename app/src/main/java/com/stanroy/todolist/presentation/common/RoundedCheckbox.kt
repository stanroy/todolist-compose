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
 * @param modifier composable modifier for main container
 * @param state checkbox state (checked, unchecked)
 * @param size checkbox size (width, height)
 * @param borderColor default checkmark border color is Gray
 * @param checkedBackground background when checkbox is checked
 * @param unCheckedBackground background when checkbox is unchecked
 * @param checkedSymbolColor color of the symbol inside checkbox when checked, default is White
 * @param uncheckedSymbolColor color of the symbol inside checkbox when unchecked, default is White
 * @param onStateChanged callback invoked when checkbox is clicked, it passes current checkbox state
 */
@Composable
fun RoundedCheckBox(
    modifier: Modifier = Modifier,
    state: Boolean = false,
    size: Dp = 20.dp,
    borderColor: Color = Color.Gray,
    checkedBackground: Color = Color.Green,
    unCheckedBackground: Color = Color.White,
    checkedSymbolColor: Color = Color.White,
    uncheckedSymbolColor: Color = Color.White,
    onStateChanged: (state: Boolean) -> Unit
) {
    val animatedBackgroundColor by animateColorAsState(targetValue = if (state) checkedBackground else unCheckedBackground)
    Box(
        modifier = modifier
            .size(size)
            .clip(shape = Shapes.small)
            .border(width = 2.5.dp, color = borderColor, shape = Shapes.small)
            .background(color = animatedBackgroundColor)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                onStateChanged(!state)
            }
    ) {
        Image(
            modifier = Modifier.padding(2.dp),
            painter = painterResource(id = R.drawable.checkmark),
            contentDescription = "check",
            colorFilter = ColorFilter.tint(if (state) checkedSymbolColor else uncheckedSymbolColor)
        )
    }
}

