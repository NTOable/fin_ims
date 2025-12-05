package com.example.supplier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.supplier.repository.PCPartsRepository
import com.example.supplier.ui.navigation.PCBuilderDrawerNav
import com.example.supplier.ui.navigation.PCBuilderNavHost
import com.example.supplier.ui.navigation.Screen
import com.example.supplier.ui.theme.PCBuilderAppTheme
import com.example.supplier.viewmodel.PCBuilderViewModel
import com.example.supplier.pcbuilderapp.SettingsRepository
import androidx.compose.foundation.layout.padding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PCPartsRepository.loadAll(this)

        setContent {
            PCBuilderAppTheme {
                PCBuilderApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PCBuilderApp() {
    val navController: NavHostController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val viewModel = PCBuilderViewModel()

    val context = LocalContext.current
    val settingsRepo = remember { SettingsRepository(context) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PCBuilderDrawerNav(navController)
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Custom PC Builder",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Screen.Settings.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            PCBuilderNavHost(
                viewModel = viewModel,
                navController = navController,
                settingsRepo = settingsRepo,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
