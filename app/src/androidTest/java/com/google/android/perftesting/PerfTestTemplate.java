/*
 * Copyright 2015, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.perftesting;

import android.os.RemoteException;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import com.google.android.perftesting.common.PerfTest;
import com.google.android.perftesting.testrules.EnableBatteryStats;
import com.google.android.perftesting.testrules.EnableExecutionTime;
import com.google.android.perftesting.testrules.EnableGraphicStats;
import com.google.android.perftesting.testrules.EnableLogcatDump;
import com.google.android.perftesting.testrules.EnableNetStatsDump;
import com.google.android.perftesting.testrules.EnableTestTracing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
//@PerfTest
public class PerfTestTemplate {

    private static final int LAUNCH_TIMEOUT = 5000;

    //If you want to change the application in this test suite, fill up the application's packagename
    @ClassRule
    public static Config config = new Config();

    public EnableTestTracing mEnableTestTracing = new EnableTestTracing();

    public EnableGraphicStats mEnableGraphicStats = new EnableGraphicStats(10);

    public EnableLogcatDump mEnableLogcatDump = new EnableLogcatDump();

    public EnableNetStatsDump mEnableNetStatsDump = new EnableNetStatsDump();

    public EnableExecutionTime mEnableExecutionTime = new EnableExecutionTime(4000);

    public EnableBatteryStats mEnableBatteryStats = new EnableBatteryStats(0.02);

    @Rule
    public TestRule chain = RuleChain
            .outerRule(mEnableLogcatDump)
            .around(mEnableTestTracing)
            .around(mEnableGraphicStats)
            .around(mEnableNetStatsDump)
            .around(mEnableBatteryStats)
            .around(mEnableExecutionTime);

    @BeforeClass
    public static void setupClass() {
        // Open the app
        config.launch(LAUNCH_TIMEOUT);
    }

    @Before
    public void setUp() {
        // Complete your setup here.
    }

    @Test
    public void performanceTest() {
        // Put operations you want to measure during the test execution here.
    }

    @After
    public void tearDown() {
        // Complete your teardown here.
    }

    @AfterClass
    public static void teardownClass() throws RemoteException {
        //Close the app
    }

}
