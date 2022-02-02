package com.ahmaddudayef.moviesmade.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.data.local.SettingPreferences
import com.ahmaddudayef.moviesmade.data.source.SettingDataSource

class SettingRepository(private val settingPreferences: SettingPreferences) : SettingDataSource {

    override suspend fun getThemeSettings(): LiveData<Boolean> {
        return settingPreferences.getThemeSetting().asLiveData()
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingPreferences.saveThemeSetting(isDarkModeActive)
    }


}