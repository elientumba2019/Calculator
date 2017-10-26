package com.example.ntumba.calculator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class MainActivity extends AppCompatActivity {


    private double baseValue , secondValue;
    private boolean resetValue;
    private int lastKey;
    private int lastOperation;


    //state variable
    public static final int DIGIT = 0;
    public static final int EQUALS = 1;
    public static final int PLUS = 2;
    public static final int MINUS = 3;
    public static final int MULTIPLY = 4;
    public static final int DIVIDE = 5;
    public static final int MODULO = 6;
    public static final int POWER = 7;
    public static final int ROOT = 8;



    //getting a reference to the screen
    @BindView(R.id.display) TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        ButterKnife.bind(MainActivity.this);

        //reset the screen
        resetValues();
    }


    /**
     * adds a new digit on screen
     * @param number
     */
    public void addDigit(int number){
        final String onScreen = getElementOnScreen();
        final String newValue = removeLeadingZero(onScreen + number);
        setValue(newValue);
    }


    /**
     * returns the Element
     * on the screen
     * @return
     */
    @NonNull
    public String getElementOnScreen() {
        return result.getText().toString().trim();
    }



    /**
     * resets the value on screen
     * if necessary
     */
    private void resetValueIfNeeded(){
        if(resetValue){
            setValueDouble(0);
        }

        resetValue = false;
    }


    /**
     * returns the numbers on Screen
     * using a double representation
     * @return
     */
    public double getDisplayedNumberAsDouble(){
        return Double.parseDouble(getElementOnScreen());
    }




    /**
     * gets the valud passed as argument and returns a new value
     * @param newValue
     * @return
     */
    private String removeLeadingZero(String newValue){
        final double value = Double.parseDouble(newValue);
        return  Formattor.doubleToString(value);
    }







    /**
     * OnClick method
     * for buttons (digits
     * @param view
     */
    @OnClick({R.id.btn_decimal,
            R.id.btn_0,
            R.id.btn_1,
            R.id.btn_2,
            R.id.btn_3,
            R.id.btn_4,
            R.id.btn_5,
            R.id.btn_6,
            R.id.btn_7,
            R.id.btn_8,
            R.id.btn_9})
    public void numPadClicked(View view){

        if(lastKey == EQUALS){
            lastOperation = EQUALS;
        }

        lastKey = DIGIT;
        resetValueIfNeeded();

        switch (view.getId()){

            case R.id.btn_decimal :
                decimalClicked();
                break;
            case R.id.btn_0 :
                zeroClicked();
                break;
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
     * handles the division mechanism
     */
    private void divideNumber(){

        double resultValue = 0;
        if(secondValue != 0){
            resultValue = baseValue / secondValue;
        }


        updateResult(resultValue);
    }


    /**
     * finds the modulo of two numbers
     */
    private void modulo(){
        double resultValue = 0;
        if(secondValue != 0){
            resultValue = baseValue % secondValue;
        }


        updateResult(resultValue);
    }


    /**
     * power function
     */
    public void powerNumber(){
        double resultValue = Math.pow(baseValue , secondValue);
        if(Double.isInfinite(resultValue) || Double.isNaN(resultValue)){
            resultValue = 0;
            updateResult(resultValue);
        }
    }



    /**
     * updates the result of some operation
     * on screen
     * operations are addition substraction multitplication
     * and division
     * @param resultV
     */
    public void updateResult(double resultV){
        setValue(Formattor.doubleToString(resultV));
        baseValue = resultV;
    }




    /**
     * handles the operations
     * @param operation
     */
    public void handleOperation(int operation){

        if(lastKey == operation){
            return;
        }



        if(lastKey == DIGIT){
            handleResult();
        }



        resetValue = true;
        lastKey = operation;
        lastOperation = operation;


        if(operation == ROOT){
            calculateResult();
        }
    }



    /**
     * actions to be taken when the decimal point is clicked on
     */
    public void decimalClicked(){

        String value = getElementOnScreen();
        if(!value.contains(".")){
            value += ".";
            setValue(value);
        }
    }



    /**
     * Actions to be taken whenever
     * the button 0 s clicked
     */
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void zeroClicked(){
        String value = getElementOnScreen();
        if(!value.isEmpty() && !value.equals("0")){
            value += "0";
            setValue(value);
        }
    }


    /**

     ont the layout
     */
    @OnClick(R.id.btn_plus)
    public void onPlusClicked(){
       handleOperation(PLUS);
    }


    /**
     * actions to tbe taked whenever the minus button is pressed
     * on tje layout
     */
    @OnClick(R.id.btn_minus)
    public void onMinusClicked(){
        handleOperation(MINUS);
    }


    /**
     * clicked event for the multiplication
     * button
     */
    @OnClick(R.id.btn_multiply)
    public void onMultiplyClicked(){
        handleOperation(MULTIPLY);
    }


    /**
     * click event for the  division
     * button
     */
    @OnClick(R.id.btn_divide)
    public void onDivisionClicked(){
        handleOperation(DIVIDE);
    }


    /**
     * click event for the modulo
     * operator
     */
    @OnClick(R.id.btn_modulo)
    public void onModuloClicked(){
        handleOperation(MODULO);
    }


    /**
     * click event for the power
     * operator
     */
    @OnClick(R.id.btn_power)
    public void onPowerClicked(){
        handleOperation(POWER);
    }




    /**
     * event handler for the root
     * button when clicked
     */
    @OnClick(R.id.btn_root)
    public void onRootClicked(){
       handleOperation(ROOT);
    }


    /**
     * clear button event
     */
    @OnClick(R.id.btn_clear)
    public void onClearClicked(){
        handleClear();
    }


    /**
     * long clear event
     * @return
     */
    @OnLongClick(R.id.btn_clear)
    public boolean onLongClear(){
        handleLongClear();
        return true;
    }


    /**
     * Equal click event
     */
    @OnClick(R.id.btn_equals)
    public void onEqualClicked(){
        handleEqual();
    }


    /**
     * onClick event for the clear
     * button when clicked
     * it clears on digit at a time
     */
    public void handleClear(){

        final String oldValue = getElementOnScreen();
        String newValue;
        final int len = oldValue.length();
        int minLength = 1;

        if(oldValue.contains("-")){
            minLength++;
        }

        if(len > minLength){
            newValue = oldValue.substring(0 , len -1);
        }
        else{
            newValue = "0";
        }


        if(newValue.equals("-0")){
            newValue = "0";
        }


       setValue(newValue);
        baseValue = Double.parseDouble(newValue);

    }


    /**
     * clears everything on screen
     * @return
     */
    public void handleLongClear(){
        resetValues();
    }






    /**
     * actions to to be performed
     * when the equal key is clicked
     */
    public void handleEqual(){

        if(lastKey == EQUALS){
            calculateResult();
        }


        if(lastKey != DIGIT){
            return;
        }



        secondValue = getDisplayedNumberAsDouble();
        calculateResult();
        lastKey = EQUALS;
    }







    /**
     * handling the equal button
     */
    public void calculateResult(){

        switch (lastOperation){

            case PLUS :
                updateResult(baseValue + secondValue);
                break;

            case MINUS :
                updateResult(baseValue - secondValue);
                break;

            case MULTIPLY :
                updateResult(baseValue * secondValue);
                break;

            case DIVIDE :
                divideNumber();
                break;

            case MODULO :
                modulo();
                break;

            case POWER :
                powerNumber();
                break;

            case ROOT:
                updateResult(Math.sqrt(baseValue));
                break;
            default :
                break;
        }
    }


    /**
     * gets the result in the base value
     */
    public void handleResult(){
        secondValue = getDisplayedNumberAsDouble();
        calculateResult();
        baseValue = getDisplayedNumberAsDouble();
    }



    /**
     * resets all the values on the screen
     */
    public void resetValues(){
        baseValue = 0;
        secondValue = 0;
        resetValue = false;
        lastKey = 0;
        lastOperation = 0;
        setValueDouble(0);
    }


    /**
     * sets the result
     * on screen
     * the result could be anything
     * @param value
     */
    public void setValueDouble(double value){
        setValue(Formattor.doubleToString(value));
    }




    /**
     * sets the value on the screen
     * @param value
     */
    public void setValue(String value){
        result.setText(value);
    }
}
