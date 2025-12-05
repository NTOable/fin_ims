package com.example.supplier.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.DeveloperBoard
import androidx.compose.material.icons.filled.SdStorage
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Toys
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.supplier.ui.theme.CorsairBlue

@Composable
fun PCBuilderDrawerNav(navController: NavController) {
    val items = listOf(
        Screen.GPU to Icons.Filled.Computer,
        Screen.CPU to Icons.Filled.Memory,
        Screen.Motherboard to Icons.Filled.DeveloperBoard,
        Screen.Memory to Icons.Filled.SdStorage,
        Screen.Storage to Icons.Filled.Storage,
        Screen.PSU to Icons.Filled.Power,
        Screen.Case to Icons.Filled.ViewModule,
        Screen.Cooling to Icons.Filled.AcUnit,
        Screen.Fans to Icons.Filled.Toys,
        Screen.AddOns to Icons.Filled.Extension,
        Screen.Overview to Icons.AutoMirrored.Filled.List
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(240.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        Text(
            text = "PC Builder",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        items.forEach { (screen, icon) ->
            val isSelected = currentRoute == screen.route
            val interaction = remember { MutableInteractionSource() }
            val isPressed = interaction.collectIsPressedAsState().value

            val iconTint = if (isPressed || isSelected) CorsairBlue else Color.White
            val textColor = if (isPressed || isSelected) CorsairBlue else MaterialTheme.colorScheme.onSurface

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = interaction,
                        indication = null
                    ) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(24.dp)
                            .background(CorsairBlue)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                } else {
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Icon(
                    icon,
                    contentDescription = screen.route,
                    tint = iconTint
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = screen.route.uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor
                )
            }
        }
    }
}
