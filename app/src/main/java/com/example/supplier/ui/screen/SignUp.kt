package com.example.supplier.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.supplier.pcbuilderapp.User
import com.example.supplier.pcbuilderapp.UserAppDatabase
import kotlinx.coroutines.launch

@Composable
fun SignUp(onSignUpSuccess: () -> Unit, onBackToSignIn: () -> Unit) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = UserAppDatabase.getDatabase(context)

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(username.value, { username.value = it }, label = { Text("Username") })
        OutlinedTextField(password.value, { password.value = it }, label = { Text("Password") })

        Button(
            onClick = {
                scope.launch {
                    val existing = db.userDao().getUser(username.value)
                    if (existing != null) {
                        message = "User already exists!"
                    } else {
                        db.userDao().insertUser(
                            User(username = username.value, password = password.value)
                        )
                        message = "User registered!"
                        onSignUpSuccess()
                    }
                }
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text("Create Account")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onBackToSignIn() }) {
            Text("Already have an account? Sign in")
        }

        Text(message)
    }
}