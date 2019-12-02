package com.aqube.espressotest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.RootMatchers.withDecorView

import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import com.aqube.espressotest.activities.LoginActivity
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsNot.not

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun emailIsEmpty() {
        onView(withId(R.id.editTextEmail)).perform(clearText())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.editTextEmail)).check(matches(withError(getString(R.string.error_field_required))))
    }

    @Test
    fun passwordIsEmpty() {
        onView(withId(R.id.editTextEmail)).perform(typeText("email@email.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(clearText())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.editTextPassword)).check(matches(withError(getString(R.string.error_field_required))))
    }

    @Test
    fun emailIsInvalid() {
        onView(withId(R.id.editTextEmail)).perform(typeText("invalid"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.editTextEmail)).check(matches(withError(getString(R.string.error_invalid_email))))
    }

    @Test
    fun passwordIsTooShort() {
        onView(withId(R.id.editTextEmail)).perform(typeText("email@email.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.editTextPassword)).check(matches(withError(getString(R.string.error_invalid_password))))
    }

    @Test
    fun loginFailed() {
        onView(withId(R.id.editTextEmail)).perform(
            typeText("incorrect@email.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withText(getString(R.string.error_login_failed)))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))
    }
    @Test
    fun loginSuccessfully_shouldShowWelcomeMessage() {
        onView(withId(R.id.editTextEmail)).perform(typeText("user@email.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withId(R.id.textViewWelcome)).check(matches(withText("Hi user@email.com!")))
    }

    @Test
    fun loginSuccessfully_shouldShowToast() {
        onView(withId(R.id.editTextEmail)).perform(typeText("user@email.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())
        onView(withText(getString(R.string.login_successfully)))
            .inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    private fun getString(@StringRes resourceId: Int): String {
        return activityRule.activity.getString(resourceId)
    }

    private fun withError(expected: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Not found error message$expected, find it!")
            }

            override fun matchesSafely(v: View): Boolean {
                return if (v is EditText) {
                    (v as EditText).error.toString() == expected
                } else false
            }
        };
    }


}