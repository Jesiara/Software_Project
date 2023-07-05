package com.bawp.heartmonitor_software_project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void testAddData(){

         Espresso.onView(withId(R.id.idRL)).check(matches(isDisplayed()));

         Espresso.onView(withId(R.id.addDetails)).check(matches(isDisplayed()));

         Espresso.onView(ViewMatchers.withId(R.id.addDetails)).perform(click()); //Click add button to add a city to the list//        onView(withId(R.id.insert)).check(matches(isDisplayed()));
         SystemClock.sleep(2000);
    }

    @Test
    public void testDeleteData(){

       // SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.idRL)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.detailsRV)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.detailsRV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(0));
        Espresso.onView(withId(R.id.card)).check(matches(isDisplayed()));
        SystemClock.sleep(40000);




        Espresso.onView(ViewMatchers.withId(R.id.btndel))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btndel))
                .perform(ViewActions.click());

    }
}