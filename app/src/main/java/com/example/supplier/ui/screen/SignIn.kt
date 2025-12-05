package com.example.dummyworkstation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.supplier.data.UserAppDatabase
import kotlinx.coroutines.launch


@Composable
fun SignIn(onLoginSuccess: () -> Unit, onGoToSignUp: () -> Unit) {
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
        Text("Sign In", style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") }
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") }
        )

        Button(
            onClick = {
                scope.launch {
                    val user = db.userDao().getUser(username.value)
                    if (user == null || user.password != password.value) {
                        message = "Invalid login!"
                    } else {
                        message = "Welcome!"
                        onLoginSuccess()
                    }
                }
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text("Log In")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = onGoToSignUp) {
            Text("Create an Account")
        }

        Text(message)
    }
}