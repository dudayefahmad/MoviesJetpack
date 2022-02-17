package com.ahmaddudayef.moviesmade.presentation.home

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.clickMenu
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.util.EspressoIdlingResource
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class HomeActivityTest {

    @get:Rule
    var rules = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovieList() {
        assertDisplayed(R.id.viewPager)
        assertDisplayed(R.id.rvMovie)
        scrollListToPosition(R.id.rvMovie, 15)
        scrollListToPosition(R.id.rvMovie, 0)
    }

    @Test
    fun loadDetailMovie() {
        assertDisplayed(R.id.viewPager)
        assertDisplayed(R.id.rvMovie)
        clickListItem(R.id.rvMovie, 5)
        assertDisplayed(R.id.image_movie_backdrop)
        assertDisplayed(R.id.poster_movie)
        assertDisplayed(R.id.title_movie)
        assertDisplayed(R.id.rating_movie)
        assertDisplayed(R.id.release_date_movie)
        clickMenu(R.id.add_to_favorite)
        clickBack()
    }

    @Test
    fun loadFavoriteMovieList() {
        assertDisplayed(R.id.viewPager)
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        assertDisplayed(R.id.rvMovie)
    }

    @Test
    fun loadTvShowList() {
        assertDisplayed(R.id.viewPager)
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        assertDisplayed(R.id.rvTvShow)
        scrollListToPosition(R.id.rvTvShow, 19)
        scrollListToPosition(R.id.rvTvShow, 0)
        assertDisplayed(R.id.rvTvShow)
    }

    @Test
    fun loadDetailTvShow() {
        assertDisplayed(R.id.viewPager)
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        assertDisplayed(R.id.rvTvShow)
        clickListItem(R.id.rvTvShow, 7)
        assertDisplayed(R.id.image_movie_backdrop)
        assertDisplayed(R.id.poster_movie)
        assertDisplayed(R.id.title_movie)
        assertDisplayed(R.id.rating_movie)
        assertDisplayed(R.id.release_date_movie)
        clickMenu(R.id.add_to_favorite)
        clickBack()
    }

    @Test
    fun loadFavoriteTvShowList() {
        assertDisplayed(R.id.viewPager)
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.tabsFavorite)).perform(selectTabAtPosition(1))
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = Matchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(TabLayout::class.java)
            )

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}