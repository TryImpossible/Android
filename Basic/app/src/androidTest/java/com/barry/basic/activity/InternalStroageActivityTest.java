package com.barry.basic.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.barry.basic.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by bynn on 2018/1/18.
 */
@RunWith(AndroidJUnit4.class)
public class InternalStroageActivityTest {

    @Rule
    public ActivityTestRule<InternalStroageActivity> mActivityRule = new ActivityTestRule<>(
            InternalStroageActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void readAccount() throws Exception {

    }

    @Test
    public void onClick() throws Exception {
        onView(withId(R.id.et_account)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
    }

}