
package com.ahmaddudayef.moviesmade.presentation.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.databinding.ActivitySettingBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_settings, SettingsFragment())
            .commit()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.title_activity_settings)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    class SettingsFragment : PreferenceFragmentCompat() {
        private lateinit var ctx: Context
        private val viewModel by viewModel<SettingViewModel>()

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val darkModeSwitch: SwitchPreferenceCompat? = findPreference("dark_mode")
            val languagePreference = findPreference<Preference>("preference_language")

            lifecycleScope.launch {
                viewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
                    if (isDarkModeActive) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        darkModeSwitch?.isChecked = true
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        darkModeSwitch?.isChecked = false
                    }
                }
            }

            darkModeSwitch?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                val switch = it as SwitchPreferenceCompat
                viewModel.saveThemeSetting(switch.isChecked)
                true
            }

            languagePreference?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                true
            }
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            ctx = context
        }
    }
}