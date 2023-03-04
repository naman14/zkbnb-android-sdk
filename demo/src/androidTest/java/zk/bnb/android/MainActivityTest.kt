package zk.bnb.android


import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.BaseMatcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        SystemClock.sleep(500);

        val appCompatEditText = onView(withId(R.id.etBaseUrl))
        appCompatEditText.perform(
            replaceText("http://192.168.68.72:8888"),
            closeSoftKeyboard()
        )
        SystemClock.sleep(200);

        val saveUrlButton = onView(withId(R.id.btnSaveUrl))
        saveUrlButton.perform(setVisibility(true), click())

        SystemClock.sleep(500);

        val executeButton = onView(withIndex(withId(R.id.btnExecute), 0))
        executeButton.perform(click())

        SystemClock.sleep(1000)

        val responseText = onView(withIndex(withId(R.id.tvResponse), 0))
        responseText.check(matches(withText("NetworkStatus(status=200, networkId=1)")))

        SystemClock.sleep(500)

        val searchEditText = onView(withId(R.id.etSearch))
        searchEditText.perform(
            replaceText("accounts"),
            closeSoftKeyboard()
        )

        SystemClock.sleep(500)


        val executeButton2 = onView(withIndex(withId(R.id.btnExecute), 0))
        executeButton2.perform(click())

        SystemClock.sleep(1000)

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

    fun setVisibility(visibile: Boolean) = object : ViewAction {
        val checkableViewMatcher = object : BaseMatcher<View>() {
            override fun matches(item: Any?): Boolean = isA(View::class.java).matches(item)
            override fun describeTo(description: Description?) {

            }
        }

        override fun getConstraints(): BaseMatcher<View> = checkableViewMatcher
        override fun getDescription(): String? = null
        override fun perform(uiController: UiController?, view: View) {
            view.isVisible = visibile
        }
    }

    private fun getElementFromMatchAtPosition(
        matcher: Matcher<View>,
        position: Int
    ): Matcher<View?>? {
        return object : BaseMatcher<View?>() {
            var counter = 0
            override fun matches(item: Any): Boolean {
                if (matcher.matches(item)) {
                    if (counter == position) {
                        counter++
                        return true
                    }
                    counter++
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("Element at hierarchy position $position")
            }
        }
    }
}
