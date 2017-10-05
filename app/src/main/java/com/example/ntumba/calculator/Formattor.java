package com.example.ntumba.calculator;
import java.text.DecimalFormat;
public class Formattor {


    /**
     * given a double value returns a reformated
     * String representation of the value
     * @param d
     * @return
     */
    public static String formatDouble(double d){
        if(d == (long)d){
            return String.format("%d" , (long)d);
        }

        else{
            final DecimalFormat format = new DecimalFormat("0.0#############");
            return format.format(d);
        }
    }
}
