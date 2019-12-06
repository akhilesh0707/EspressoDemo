package com.aqube.espressotest.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.aqube.espressotest.R
import com.aqube.espressotest.adapter.CustomAdapter
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@FixMethodOrder(MethodSorters.DEFAULT)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun testSampleRecyclerVisible() {
        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView))
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerClick() {
        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView))
            )
            .perform(RecyclerViewActions.actionOnItemAtPosition<CustomAdapter.ViewHolder>(0, click()))
    }

    @Test
    fun testCaseForRecyclerScroll() {
        // Get total item of RecyclerView
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        val itemCount = recyclerView.adapter!!.itemCount

        // Scroll to end of page with position
        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView))
            )
            .perform(RecyclerViewActions.scrollToPosition<CustomAdapter.ViewHolder>(itemCount - 1))
    }

    @Test
    fun testCaseForRecyclerItemView() {
        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView))
            )
            .check(
                matches(
                    withViewAtPosition(
                        1, Matchers.allOf<View>(withId(R.id.textViewUsername), isDisplayed())
                    )
                )
            )
    }

    private fun withViewAtPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}