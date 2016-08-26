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

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import com.google.android.perftesting.common.PerfTest;
import com.google.android.perftesting.testrules.EnableLogcatDump;
import com.google.android.perftesting.testrules.EnableNetStatsDump;
import com.google.android.perftesting.testrules.EnableTestTracing;
import com.google.android.perftesting.testrules.MeasureBatteryStats;
import com.google.android.perftesting.testrules.MeasureExecutionTime;
import com.google.android.perftesting.testrules.MeasureGraphicStats;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
@PerfTest
public class MyPerfTest {
    private static final int LAUNCH_TIMEOUT = 5000;

    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private UiObject2 mObject;

    public MeasureBatteryStats mMeasureBatteryStats = new MeasureBatteryStats(0.05);
    public MeasureExecutionTime mMeasureExecutionTime = new MeasureExecutionTime(7000);
    public MeasureGraphicStats mMeasureGraphicStats = new MeasureGraphicStats(10);

    @Rule
    public TestRule chain = RuleChain
            .outerRule(mMeasureGraphicStats)
            .around(mMeasureBatteryStats)
            .around(mMeasureExecutionTime);


    @Before
    public void setUp() {
        // Open the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(Config.TARGET_PACKAGE_NAME);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the view to appear
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.wait(Until.hasObject(By.pkg(Config.TARGET_PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void PowerConsumption() throws InterruptedException {
        // Put operations you want to measure during the test execution here.

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mMeasureBatteryStats.setpowerUseThresholdMah(0.01);

        int displayWidth = mDevice.getDisplayWidth();
        int displayHeight = mDevice.getDisplayHeight();
        //scroll view
        for (int i = 0; i <= 7; i++) {
            mDevice.swipe(displayWidth / 2, (int) (displayHeight* .9),
                    displayWidth / 2, (int)(displayHeight* .25), 20);

            Thread.sleep(3000);
        }

        mMeasureBatteryStats.end();
    }

    @Test
    public void ResponseTime() throws InterruptedException {
        // Put operations you want to measure during the test execution here.

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mMeasureExecutionTime.begin();

        mDevice.findObject(By.text("類型")).click();
        mDevice.wait(Until.hasObject(By.text("華語")), LAUNCH_TIMEOUT);

        mMeasureExecutionTime.end();
    }


    @Test
    public void Smoothness() throws InterruptedException {
        // Put operations you want to measure during the test execution here.

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mMeasureGraphicStats.begin();

        int displayWidth = mDevice.getDisplayWidth();
        int displayHeight = mDevice.getDisplayHeight();
        //scroll view
        for (int i = 0; i <= 4; i++) {
            mDevice.swipe(displayWidth / 2, (int) (displayHeight* .9),
                    displayWidth / 2, (int)(displayHeight* .25), 20);

            //Thread.sleep(2000);
        }

        mMeasureGraphicStats.end();
    }



}
