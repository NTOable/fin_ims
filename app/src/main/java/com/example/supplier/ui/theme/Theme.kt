package com.example.supplier.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.pcbuilderapp.R

private val DarkColorScheme = darkColorScheme(
    primary = CorsairBlue,
    secondary = CorsairBlue,
    background = CorsairDark,
    surface = CorsairSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = CorsairText,
    onSurface = CorsairText
)

val CorsairFontFamily = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_regular, FontWeight.Normal)
)

val CorsairTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = CorsairFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = CorsairFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

@Composable
fun PCBuilderAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = CorsairTypography,
        content = content
    )
}
