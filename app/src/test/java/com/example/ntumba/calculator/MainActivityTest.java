package com.example.ntumba.calculator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

/**
 * Created by ntumba on 17-10-14.
 */


@RunWith(MyTestRunner.class)
@Config(constants = BuildConfig.class , manifest = "app/src/main/AndroidManifest.xml" , sdk = 23)
public class MainActivityTest {


    MainActivity activity;


    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }
}
