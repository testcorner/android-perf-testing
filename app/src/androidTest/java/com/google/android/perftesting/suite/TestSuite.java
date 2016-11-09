package com.google.android.perftesting.suite;

import com.google.android.perftesting.RepeatSample.Launch;
import com.google.android.perftesting.common.PerfTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by nicole on 2016/11/2.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({Launch.class, AllTests.class})
@PerfTest
public class TestSuite {

}
