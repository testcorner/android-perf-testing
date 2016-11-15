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

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import com.google.android.perftesting.Config;
import com.google.android.perftesting.common.PerfTest;
import com.google.android.perftesting.testrules.MeasureExecutionTime;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
@PerfTest
public class Launch {
    private static final int LAUNCH_TIMEOUT = 2000;
    private static final String LOG_TAG = "ExecTimeSample";
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @ClassRule
    public static Config config = new Config("com.skysoft.kkbox.android");

    @BeforeClass
    public static void setupClass() {
        // Open the app
        config.launch(LAUNCH_TIMEOUT);
    }

    @Test
    public void Test(){

    }

    @AfterClass
    public static void teardownClass() {
        //Close the app
    }
}
