package com.example.ntumba.calculator;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //getting a reference to the screen
    @BindView(R.id.display) TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        ButterKnife.bind(MainActivity.this);
    }


    /**
     * adds a new digit on screen
     * @param number
     */
    private void addDigit(int number){
        final String onScreen = GetElemenOnScreen();
        final String newVar = getValue(onScreen + number);
        result.setText(newVar);
    }


    /**
     * returns the Element
     * on the screen
     * @return
     */
    @NonNull
    private String GetElemenOnScreen() {
        return result.getText().toString().trim();
    }


    /**
     * gets the valud passed as argument and returns a new value
     * @param newValue
     * @return
     */
    private String getValue(String newValue){
        double value = Double.parseDouble(newValue);
        return  formatDouble(value);
    }



    /**
     * given a double value returns a reformated
     * String representation of the value
     * @param d
     * @return
     */
    @SuppressLint("DefaultLocale")
    private String formatDouble(double d){
        if(d == (long)d){
            return String.format("%d" , (long)d);
        }

        else{
            final DecimalFormat format = new DecimalFormat("0.0############");
            return format.format(d);
        }
    }



    /**
     * OnClick method
     * for buttons (digits
     * @param view
     */
    @OnClick({R.id.btn_1, R.id.btn_2,
            R.id.btn_3, R.id.btn_4, R.id.btn_5,
            R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9})
    public void digitClicked(View view){

        switch (view.getId()){

            case R.id.btn_1:
                addDigit(1);
                break;
            case R.id.btn_2:
                addDigit(2);
                break;
            case R.id.btn_3:
                addDigit(3);
                break;
            case R.id.btn_4:
                addDigit(4);
                break;
            case R.id.btn_5:
                addDigit(5);
                break;
            case R.id.btn_6:
                addDigit(6);
                break;
            case R.id.btn_7:
                addDigit(7);
                break;
            case R.id.btn_8:
                addDigit(8);
                break;
            case R.id.btn_9:
                addDigit(9);
                break;
            default:
                break;
        }
    }


    /**
     * actions to be taken when the decimal point is clicked on
     */
    @OnClick(R.id.btn_decimal)
    public void decimalClicked(){

        String value = GetElemenOnScreen();
        if(!value.contains(".")){
            value += ".";
            result.setText(value);
        }
    }



    /**
     * Actions to be taken whenever
     * the button 0 s clicked
     */
    @OnClick(R.id.btn_0)
    public void zeroClicked(){
        String value = GetElemenOnScreen();
        if(!value.isEmpty() && !value.equals("0")){
            value += "0";
            result.setText(value);
        }
    }
}
