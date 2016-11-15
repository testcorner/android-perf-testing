package com.google.android.perftesting.suite;

import com.google.android.perftesting.testrules.EnableRepeatSuite;
import com.google.android.perftesting.RepeatSample.RadioStation;
import com.google.android.perftesting.RepeatSample.Settings;
import com.google.android.perftesting.RepeatSample.Together;
import com.google.android.perftesting.common.PerfTest;


import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by nicole on 2016/11/2.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({RadioStation.class, Together.class, Settings.class})
@PerfTest
public class AllTests {

    @ClassRule
    public static EnableRepeatSuite repeatRule = new EnableRepeatSuite(30);

}
