package com.ahmaddudayef.moviesmade.presentation.setting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.data.source.SettingDataSource
import com.ahmaddudayef.moviesmade.mock.MockSettingRepository
import com.ahmaddudayef.moviesmade.util.getTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SettingViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<SettingViewModel>()

    @Mock
    private lateinit var settingRepository: SettingDataSource

    private val mockSettingRepository = MockSettingRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    SettingViewModel(
                        settingRepository
                    )
                }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `getThemeSettings success`() {
        testDispatcher.runBlockingTest {
            val theme = mockSettingRepository.getThemeSettings()
            val expected = listOf(true)
            BDDMockito.given(settingRepository.getThemeSettings()).willReturn(theme)
            val actual = viewModel.getThemeSettings().getTestObserver().observedValues
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `toggle darkmode theme switch`() {
        testDispatcher.runBlockingTest {
            viewModel.saveThemeSetting(true)
            BDDMockito.verify(settingRepository).saveThemeSetting(true)
        }
    }
}