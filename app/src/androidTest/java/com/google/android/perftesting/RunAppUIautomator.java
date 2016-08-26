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
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.google.android.perftesting.common.PerfTest;
import com.google.android.perftesting.testrules.EnableLogcatDump;
import com.google.android.perftesting.testrules.EnableNetStatsDump;
import com.google.android.perftesting.testrules.MeasureBatteryStats;
import com.google.android.perftesting.testrules.MeasureGraphicStats;
import com.google.android.perftesting.testrules.EnableTestTracing;
import com.google.android.perftesting.testrules.MeasureExecutionTime;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;

//-----------------for rule chain--------------------//

import org.junit.rules.TestRule;
import org.junit.rules.RuleChain;

/**
 * For a small sample on just the Espresso framework see https://goo.gl/GOUP47
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
// TODO(developer): Uncomment the below annotation to have this test added to the set of perf tests.
// @PerfTest
public class RunAppUIautomator extends RunListener {
    private static final String LOG_TAG = "RunAppUIautomator";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private static UiDevice Device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

//    @Rule
//    public Timeout globalTimeout= new Timeout(
//            SCROLL_TIME_IN_MILLIS + MAX_ADAPTER_VIEW_PROCESSING_TIME_IN_MILLIS, TimeUnit.MILLISECONDS);

    //--------------------rule chain: order testrules---------------------//

    public EnableTestTracing mEnableTestTracing = new EnableTestTracing();

    public MeasureGraphicStats mMeasureGraphicStats = new MeasureGraphicStats(10);

    public EnableLogcatDump mEnableLogcatDump = new EnableLogcatDump();

    public EnableNetStatsDump mEnableNetStatsDump = new EnableNetStatsDump();

    public MeasureExecutionTime mMeasureExecutionTime = new MeasureExecutionTime(4000);

    public MeasureBatteryStats mMeasureBatteryStats = new MeasureBatteryStats(0.02);

    @Rule
    public TestRule chain = RuleChain
            .outerRule(mEnableLogcatDump)
            .around(mEnableTestTracing)
            .around(mMeasureGraphicStats)
            .around(mEnableNetStatsDump)
            .around(mMeasureBatteryStats)
            .around(mMeasureExecutionTime);

    //----------Beforeclass: Run Before Testcase------------//
    @BeforeClass
    public static void setup(){
         //open the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(Config.TARGET_PACKAGE_NAME);
         //Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        //Wait for the view to appear
        Device.wait(Until.hasObject(By.res(Config.TARGET_PACKAGE_NAME + ":id/view_runway")), LAUNCH_TIMEOUT);
    }

    //---------------------Testcase----------------------//
    @Test
    @PerfTest
    public void startSwip1() throws InterruptedException, UiObjectNotFoundException {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //scroll view
        int displayWidth = mDevice.getDisplayWidth();
        int displayHeight = mDevice.getDisplayHeight();

        for (int i = 0; i <= 2; i++) {
            mDevice.swipe(displayWidth / 2, (int) (displayHeight* .9),
                    displayWidth / 2, (int)(displayHeight* .25), 20);

            Thread.sleep(2000);
        }

    }

    @Test
    @PerfTest
    public void startSwip2() throws InterruptedException, UiObjectNotFoundException {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //scroll view
        int displayWidth = mDevice.getDisplayWidth();
        int displayHeight = mDevice.getDisplayHeight();

        for (int i = 0; i <= 3; i++) {
            mDevice.swipe(displayWidth / 2, (int) (displayHeight* .9),
                    displayWidth / 2, (int)(displayHeight* .25), 20);

            Thread.sleep(2000);
        }

    }

    //----------------After: Run After Testcase----------------//
    @After
    public void teardown() throws InterruptedException, UiObjectNotFoundException {
        // Initialize UiDevice instance

    }

}
