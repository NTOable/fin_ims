package com.example.supplier.ui.navigation

sealed class Screen(val route: String) {
    object GPU : Screen("gpu")
    object CPU : Screen("cpu")
    object Motherboard : Screen("motherboard")
    object Memory : Screen("memory")
    object Storage : Screen("storage")
    object PSU : Screen("psu")
    object Case : Screen("case")
    object Cooling : Screen("cooling")
    object Fans : Screen("fans")
    object AddOns : Screen("addons")
    object Overview : Screen("overview")

    object Settings : Screen("settings")
}
