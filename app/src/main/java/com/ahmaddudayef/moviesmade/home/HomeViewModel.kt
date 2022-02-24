package com.ahmaddudayef.moviesmade.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahmaddudayef.moviesmade.core.data.source.SettingDataSource

class HomeViewModel(private val settingDataSource: SettingDataSource) : ViewModel() {
    suspend fun getThemeSettings(): LiveData<Boolean> {
        return settingDataSource.getThemeSettings()
    }
}