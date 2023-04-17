package com.stanroy.todolist.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stanroy.todolist.presentation.theme.defaultRoundedCornerShape

@Composable
fun RoundedTextField(
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    fieldTitle: String,
    value: String,
    onValueChange: (String) -> Unit,
    minHeight: Dp = Dp.Unspecified,
    maxLines: Int,
    singleLine: Boolean,
    imeAction: ImeAction = ImeAction.Default
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        if (fieldTitle.isNotEmpty()) Text(
            modifier = Modifier.padding(start = 16.dp),
            text = fieldTitle,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onPrimary
        )
        TextField(
            modifier = textFieldModifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = minHeight),
            value = value,
            onValueChange = onValueChange,
            maxLines = maxLines,
            singleLine = singleLine,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            shape = defaultRoundedCornerShape,
            keyboardOptions = KeyboardOptions(imeAction = imeAction)
        )
    }
}