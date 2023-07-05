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
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class InsertActivityTest {

    @Rule
    public ActivityScenarioRule<InsertActivity> activity_rule = new ActivityScenarioRule<>(InsertActivity.class);

    @Test
    public void testAddData() {


        onView(withId(R.id.insert)).check(matches(isDisplayed()));
        onView(withText("Enter data")).check(matches(isDisplayed()));
        onView(withId(R.id.date)).check(matches(isDisplayed()));
        onView(withId(R.id.date)).perform(typeText("6/5/2023")); //Type a city name
        onView(withId(R.id.time)).check(matches(isDisplayed()));
        onView(withId(R.id.time)).perform(typeText("12.55")); //Type a city name
        onView(withId(R.id.systolic)).check(matches(isDisplayed()));
        onView(withId(R.id.systolic)).perform(typeText("100")); //Type a city name
        onView(withId(R.id.diastolic)).check(matches(isDisplayed()));
        onView(withId(R.id.diastolic)).perform(typeText("100"));
        Espresso.pressBack();
        onView(withId(R.id.heartRate)).check(matches(isDisplayed()));
        onView(withId(R.id.heartRate)).perform(typeText("45")); //Type a city name
        Espresso.pressBack();
        onView(withId(R.id.Comment)).check(matches(isDisplayed()));
        onView(withId(R.id.Comment)).perform(typeText("first_test")); //Type a city name
        Espresso.pressBack();
        onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
        onView(withId(R.id.saveButton)).perform(click());

    }
}