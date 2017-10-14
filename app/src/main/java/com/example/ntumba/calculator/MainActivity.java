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
    private static final int DIGIT = 0;
    private static final int EQUALS = 1;
    private static final int PLUS = 2;
    private static final int MINUS = 3;
    private static final int MULTIPLY = 4;
    private static final int DIVIDE = 5;
    private static final int MODULO = 6;
    private static final int POWER = 7;



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
    private void addDigit(int number){
        final String onScreen = getElementOnScreen();
        final String newVar = removeLeadingZero(onScreen + number);
        result.setText(newVar);
    }


    /**
     * returns the Element
     * on the screen
     * @return
     */
    @NonNull
    private String getElementOnScreen() {
        resetValueIfNeeded();
        return result.getText().toString().trim();
    }



    /**
     * resets the value on screen
     * if necessary
     */
    private void resetValueIfNeeded(){
        if(resetValue){
            result.setText("0");
        }

        resetValue = false;
    }


    /**
     * returns the numbers on Screen
     * using a double representation
     * @return
     */
    private double getDisplayedNumberAsDouble(){
        return Double.parseDouble(result.getText().toString().trim());
    }




    /**
     * gets the valud passed as argument and returns a new value
     * @param newValue
     * @return
     */
    private String removeLeadingZero(String newValue){
        final double value = Double.parseDouble(newValue);
        return  Formattor.formatDouble(value);
    }







    /**
     * OnClick method
     * for buttons (digits
     * @param view
     */
    @OnClick({R.id.btn_decimal ,R.id.btn_0 , R.id.btn_1, R.id.btn_2,
            R.id.btn_3, R.id.btn_4, R.id.btn_5,
            R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9})
    public void numPadClicked(View view){

        lastKey = DIGIT;

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
     * updates the result of some operation
     * on screen
     * operations are addition substraction multitplication
     * and division
     * @param resultV
     */
    public void updateResult(double resultV){
        result.setText(Formattor.formatDouble(resultV));
        baseValue = resultV;
    }




    /**
     * handles the operations
     * @param operation
     */
    private void handleOperation(int operation){

        if(lastKey == operation){
            return;
        }



        if(lastKey == DIGIT){
            getResult();
        }



        resetValue = true;
        lastKey = operation;
        lastOperation = operation;
    }



    /**
     * actions to be taken when the decimal point is clicked on
     */
    public void decimalClicked(){

        String value = getElementOnScreen();
        if(!value.contains(".")){
            value += ".";
            result.setText(value);
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
            result.setText(value);
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
        getResult();
        lastOperation = EQUALS;
        updateResult(Math.sqrt(baseValue));
    }


    /**
     * onClick event for the clear
     * button when clicked
     * it clears on digit at a time
     */
    @OnClick(R.id.btn_clear)
    public void onClearClicked(){

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


        result.setText(newValue);
        baseValue = Double.parseDouble(newValue);

    }


    /**
     * clears everything on screen
     * @return
     */
    @OnLongClick(R.id.btn_clear)
    public boolean clearLongClicked(){
        resetValues();
        return  true;
    }






    /**
     * actions to to be performed
     * when the equal key is clicked
     */
    @OnClick(R.id.btn_equals)
    public void onEqualClicked(){

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
    private void calculateResult(){

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
                updateResult(Math.pow(baseValue , secondValue));
                break;
        }
    }


    /**
     * gets the result in the base value
     */
    public void getResult(){
        secondValue = getDisplayedNumberAsDouble();
        calculateResult();
        baseValue = getDisplayedNumberAsDouble();
    }



    /**
     * resets all the values on the screen
     */
    private void resetValues(){
        baseValue = 0;
        secondValue = 0;
        resetValue = false;
        lastKey = 0;
        lastOperation = 0;
        result.setText("0");
    }
}
