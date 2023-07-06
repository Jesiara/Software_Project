package com.bawp.heartmonitor_software_project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAppName() {
        onView(withText("HeartMonitor_Software_Project")).check(matches(isDisplayed())); //Check the name on the screen
    }


    @Test
    public void testSplash()
    {
        onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(withText("Heart Monitor")));
        onView(withId(R.id.developtxtid)).check(matches(withText("Developed By")));
        onView(withId(R.id.developtxtid2)).check(matches(withText("Jesi | Arpa")));

    }





}