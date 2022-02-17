package com.ahmaddudayef.moviesmade.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmaddudayef.moviesmade.data.source.SettingDataSource

class MockSettingDataSource : SettingDataSource {
    override suspend fun getThemeSettings(): LiveData<Boolean> {
        return isDarkMode
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
    }

    companion object {
        val isDarkMode = MutableLiveData(true)
    }
}