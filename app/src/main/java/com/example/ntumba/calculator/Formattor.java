package com.example.ntumba.calculator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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
            final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setDecimalSeparator('.');

            final DecimalFormat formatter = new DecimalFormat();
            formatter.setMaximumIntegerDigits(12);
            formatter.setMaximumFractionDigits(12);
            formatter.setDecimalFormatSymbols(symbols);
            formatter.setGroupingUsed(false);
            return  formatter.format(d);
        }
    }
}
