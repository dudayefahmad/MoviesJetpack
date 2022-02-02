package com.ahmaddudayef.moviesmade.presentation.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.source.SettingDataSource
import kotlinx.coroutines.launch

class SettingViewModel(private val settingDataSource: SettingDataSource) : ViewModel() {

    suspend fun getThemeSettings(): LiveData<Boolean> {
        return settingDataSource.getThemeSettings()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingDataSource.saveThemeSetting(isDarkModeActive)
        }
    }
}