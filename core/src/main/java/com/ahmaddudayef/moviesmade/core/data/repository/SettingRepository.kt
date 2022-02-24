package com.ahmaddudayef.moviesmade.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.data.local.datastore.SettingPreferences
import com.ahmaddudayef.moviesmade.core.data.source.SettingDataSource

class SettingRepository(private val settingPreferences: SettingPreferences) :
    SettingDataSource {

    override suspend fun getThemeSettings(): LiveData<Boolean> {
        return settingPreferences.getThemeSetting().asLiveData()
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingPreferences.saveThemeSetting(isDarkModeActive)
    }


}