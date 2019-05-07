package com.example.vamsi.easy_parking;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);

    MainActivity mainActivity;
    @Before
    public void setUp() throws Exception {

        mainActivity=activityTestRule.getActivity();

    }

    @Test
    public void onActivityLaunch(){

        View v =mainActivity.findViewById(R.id.progress);
        assertNotNull(v);

    }
    @After
    public void tearDown() throws Exception {

    }


}