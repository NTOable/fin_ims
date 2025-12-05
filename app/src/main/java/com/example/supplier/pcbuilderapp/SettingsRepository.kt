package com.example.supplier.pcbuilderapp

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore by preferencesDataStore(name = "settings")


object SettingsKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode")
    val AUTO_SAVE = booleanPreferencesKey("auto_save")
    val STRICT_COMPAT = booleanPreferencesKey("strict_compatibility")
}

class SettingsRepository(private val context: Context) {


    val darkModeFlow: Flow<Boolean> = context.settingsDataStore.data
        .map { prefs -> prefs[SettingsKeys.DARK_MODE] ?: true }

    val autoSaveFlow: Flow<Boolean> = context.settingsDataStore.data
        .map { prefs -> prefs[SettingsKeys.AUTO_SAVE] ?: true }

    val strictCompatFlow: Flow<Boolean> = context.settingsDataStore.data
        .map { prefs -> prefs[SettingsKeys.STRICT_COMPAT] ?: false }

    suspend fun setDarkMode(enabled: Boolean) {
        context.settingsDataStore.edit { prefs -> prefs[SettingsKeys.DARK_MODE] = enabled }
    }

    suspend fun setAutoSave(enabled: Boolean) {
        context.settingsDataStore.edit { prefs -> prefs[SettingsKeys.AUTO_SAVE] = enabled }
    }

}
