package com.ahmaddudayef.moviesmade.core.data.source

import androidx.lifecycle.LiveData

interface SettingDataSource {
    suspend fun getThemeSettings(): LiveData<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}