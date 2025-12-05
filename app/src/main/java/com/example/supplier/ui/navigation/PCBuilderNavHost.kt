package com.example.supplier.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.supplier.viewmodel.PCBuilderViewModel
import com.example.supplier.pcbuilderapp.SettingsRepository
import com.example.supplier.ui.screen.AddOnSelectionScreen
import com.example.supplier.ui.screen.CPUSelectionScreen
import com.example.supplier.ui.screen.CaseSelectionScreen
import com.example.supplier.ui.screen.CoolingSelectionScreen
import com.example.supplier.ui.screen.FanSelectionScreen
import com.example.supplier.ui.screen.GPUSelectionScreen
import com.example.supplier.ui.screen.MemorySelectionScreen
import com.example.supplier.ui.screen.MotherboardSelectionScreen
import com.example.supplier.ui.screen.OverviewScreen
import com.example.supplier.ui.screen.PSUSelectionScreen
import com.example.supplier.ui.screen.SettingsScreen
import com.example.supplier.ui.screen.StorageSelectionScreen

@Composable
fun PCBuilderNavHost(
    viewModel: PCBuilderViewModel,
    navController: NavHostController,
    settingsRepo: SettingsRepository,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.GPU.route,
        modifier = modifier
    ) {
        composable(Screen.GPU.route) { GPUSelectionScreen(viewModel) }
        composable(Screen.CPU.route) { CPUSelectionScreen(viewModel) }
        composable(Screen.Motherboard.route) { MotherboardSelectionScreen(viewModel) }
        composable(Screen.Memory.route) { MemorySelectionScreen(viewModel) }
        composable(Screen.Storage.route) { StorageSelectionScreen(viewModel) }
        composable(Screen.PSU.route) { PSUSelectionScreen(viewModel) }
        composable(Screen.Case.route) { CaseSelectionScreen(viewModel) }
        composable(Screen.Cooling.route) { CoolingSelectionScreen(viewModel) }
        composable(Screen.Fans.route) { FanSelectionScreen(viewModel) }
        composable(Screen.AddOns.route) { AddOnSelectionScreen(viewModel) }
        composable(Screen.Overview.route) { OverviewScreen(viewModel) }
        composable(Screen.Settings.route) {
            SettingsScreen(settingsRepo, navController)
        }
    }
}
