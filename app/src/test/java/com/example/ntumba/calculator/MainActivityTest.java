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
        String res = calcResult(-1.2 , MainActivity.PLUS , 3.4);
        assertEquals("2.2" , res);
    }


    /**
     * substraction
     */
    @Test
    public void substractionTest(){
        String res = calcResult(7.8 , MainActivity.MINUS , 2.5);
        assertEquals("5.3" , res);
    }


    /**
     * multiplication test
     */
    @Test
    public void multiplyTest(){
        String res = calcResult(-3.2 , MainActivity.MULTIPLY , 6.6);
        assertEquals("-21.12" , res);
    }


    /**
     * division test
     */
    @Test
    public void divisionTest(){
        String res = calcResult(18.25 , MainActivity.DIVIDE , 5);
        assertEquals("3.65" , res);
    }

    /**
     * zero division test
     */
    @Test
    public void divisionByZero(){
        String res = calcResult(6 , MainActivity.DIVIDE , 0);
        assertEquals("0" , res);
    }


    /**
     * modulo test
     */
    @Test
    public void moduloTest(){
        String res = calcResult(6.5 , MainActivity.MODULO , 3);
        assertEquals("0.5" , res);
    }


    /**
     * power test
     */
    @Test
    public void powerTest(){
        String res = calcResult(3 , MainActivity.POWER , 6);
        assertEquals("729" , res);
    }


    /**
     * root test
     */
    @Test
    public void rootTest(){
        setDouble(16);
        handleOperation(MainActivity.ROOT);
        activity.handleResult();
        assertEquals("4" , getDisplayedNumber());
    }




    /**
     * handle operation given the int
     * operation
     * @param operation
     */
    private void handleOperation(int operation){
        activity.handleOperation(operation);
    }






    /**
     * computation method
     * @param baseValue
     * @param operation
     * @param secondValue
     * @return
     */
    private String calcResult(double baseValue , int operation , double secondValue){
        setDouble(baseValue);
        handleOperation(operation);
        setDouble(secondValue);
        activity.handleResult();
        return getDisplayedNumber();

    }



    /**
     * simulates putting number on screen
     * @param d
     */
    private void setDouble(double d){
        activity.setValueDouble(d);
    }


    /**
     * simulates getting th numer on screen
     * @return
     */
    private String getDisplayedNumber(){
        return activity.getElementOnScreen();
    }
}
