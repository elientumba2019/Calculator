package com.example.ntumba.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import static junit.framework.Assert.assertEquals;
import butterknife.ButterKnife;

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
        ButterKnife.bind(activity);
    }


    /**
     * test for the addition
     */
    @Test
    public void addSimpleDigit(){
        activity.addDigit(2);
        assertEquals(2.0 , activity.getDisplayedNumberAsDouble());
    }


    /**
     * leading zero test
     */
    @Test
    public void removeLeadingZero(){
        activity.addDigit(0);
        activity.addDigit(5);
        assertEquals(5.0 , activity.getDisplayedNumberAsDouble());
    }


    /**
     * addition test
     */
    @Test
    public void additionTest(){
        activity.setResult("-1.2");
        activity.handleOperation(MainActivity.PLUS);
        activity.setResult("3.4");
        activity.getResult();
        assertEquals(2.2 , activity.getDisplayedNumberAsDouble());
    }
}
