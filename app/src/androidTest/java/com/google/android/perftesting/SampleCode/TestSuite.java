package com.google.android.perftesting.SampleCode;

import com.google.android.perftesting.testrules.EnableRepeatSuite;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by nicole on 2016/11/2.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ExecTimeSample.class, SmoothnessSample.class})
public class TestSuite {

    @ClassRule
    public static EnableRepeatSuite repeatRule = new EnableRepeatSuite(60);

}
