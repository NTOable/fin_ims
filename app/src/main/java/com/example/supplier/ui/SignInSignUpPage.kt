package com.example.supplier.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.supplier.ui.screen.SignIn
import com.example.supplier.ui.screen.SignUp


class SignInSignUpPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember { mutableStateOf("signin") }

            when (screen) {
                "signin" -> SignIn(
                    onLoginSuccess = { screen = "home" },
                    onGoToSignUp = { screen = "signup" }
                )

                "signup" -> SignUp(
                    onSignUpSuccess = { screen = "signin" }, onBackToSignIn = { screen = "signin" }
                )

                "home" -> Text("Logged In Successfully!")
            }

        }
    }
}