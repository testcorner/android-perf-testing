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

package com.google.android.perftesting.RepeatSample;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import com.google.android.perftesting.common.PerfTest;
import com.google.android.perftesting.common.Repeat;
import com.google.android.perftesting.testrules.EnableRepeatCase;
import com.google.android.perftesting.testrules.MeasureExecutionTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
@PerfTest
public class RadioStation {
    private static final int LAUNCH_TIMEOUT = 2000;
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Rule
    public MeasureExecutionTime mMeasureExecutionTime = new MeasureExecutionTime(1000);

    @Rule
    public EnableRepeatCase repeatCase = new EnableRepeatCase();

    @Before
    public void setUp() {
        // Complete your setup here.
       mDevice.wait(Until.hasObject(By.res("com.skysoft.kkbox.android:id/toolbar")), LAUNCH_TIMEOUT);
    }

    @Test
    public void radioStation() {
        // Put operations you want to measure during the test execution here.

        mDevice.findObject(By.desc("打開主選單")).click();
        mDevice.waitForIdle();
        mDevice.wait(Until.findObject(By.desc("電台")), LAUNCH_TIMEOUT).click();

        mMeasureExecutionTime.begin();

        mDevice.wait(Until.findObject(By.res("com.skysoft.kkbox.android:id/view_icon")), LAUNCH_TIMEOUT);

        mMeasureExecutionTime.end();

        mDevice.pressBack();
    }

    @After
    public void tearDown() {
        // Complete your teardown here.
    }
}
