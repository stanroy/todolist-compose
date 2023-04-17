package com.stanroy.todolist.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.stanroy.todolist.R


object TodoAppTypography {
    private val defaultAlignment = TextAlign.Start

    private val montserratFamily = FontFamily(
        Font(R.font.montserrat_light, FontWeight.Light),
        Font(R.font.montserrat_regular, FontWeight.Normal),
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.montserrat_bold, FontWeight.Bold),
    )

    val typography = Typography(
        h1 = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = defaultAlignment,
        ),
        subtitle1 = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = defaultAlignment,
        ),
        body1 = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = defaultAlignment,
        ),
        body2 = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            textAlign = defaultAlignment,
        ),
        button = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            textAlign = defaultAlignment,
        )
    )
}

