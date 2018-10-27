package com.thetasolutions.calculator.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thetasolutions.calculator.R;
import com.thetasolutions.calculator.activity.Constants.AppConstants;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView txtExpression, txtResult;
    ImageView imgBackSpace;
    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight,
            btnNine, btnMinus, btnPlus, btnMultiply, btnDivide, btnClear, btnOpen, btnClose,
            btnEquals, btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    void bindViews(){
        txtExpression=findViewById(R.id.txt_expression);
        txtResult=findViewById(R.id.txt_result);

        //number buttons
        btnZero=findViewById(R.id.btn_zero);
        btnOne=findViewById(R.id.btn_one);
        btnTwo=findViewById(R.id.btn_two);
        btnThree=findViewById(R.id.btn_three);
        btnFour=findViewById(R.id.btn_four);
        btnFive=findViewById(R.id.btn_five);
        btnSix=findViewById(R.id.btn_six);
        btnSeven=findViewById(R.id.btn_seven);
        btnEight=findViewById(R.id.btn_eight);
        btnNine=findViewById(R.id.btn_nine);

        //action buttons
        btnMinus=findViewById(R.id.btn_minus);
        btnPlus=findViewById(R.id.btn_plus);
        btnMultiply=findViewById(R.id.btn_multiply);
        btnDivide=findViewById(R.id.btn_divide);
        btnDot=findViewById(R.id.btn_dot);
        btnClear=findViewById(R.id.btn_clear);
        btnEquals=findViewById(R.id.btn_equals);
        btnOpen=findViewById(R.id.btn_open);
        btnClose=findViewById(R.id.btn_close);
        imgBackSpace=findViewById(R.id.img_back);

        //implement click listeners
        btnZero.setOnClickListener(new NumberButtonClickListener());
        btnOne.setOnClickListener(new NumberButtonClickListener());
        btnTwo.setOnClickListener(new NumberButtonClickListener());
        btnThree.setOnClickListener(new NumberButtonClickListener());
        btnFour.setOnClickListener(new NumberButtonClickListener());
        btnFive.setOnClickListener(new NumberButtonClickListener());
        btnSix.setOnClickListener(new NumberButtonClickListener());
        btnSeven.setOnClickListener(new NumberButtonClickListener());
        btnEight.setOnClickListener(new NumberButtonClickListener());
        btnNine.setOnClickListener(new NumberButtonClickListener());
        btnDot.setOnClickListener(new NumberButtonClickListener());

        btnOpen.setOnClickListener(new OperationButtonListener());
        btnClose.setOnClickListener(new OperationButtonListener());

        btnMinus.setOnClickListener(new OperationButtonListener());
        btnPlus.setOnClickListener(new OperationButtonListener());
        btnMultiply.setOnClickListener(new OperationButtonListener());
        btnDivide.setOnClickListener(new OperationButtonListener());

        btnClear.setOnClickListener(new ActionButtonsClickListener());
        imgBackSpace.setOnClickListener(new ActionButtonsClickListener());
        btnEquals.setOnClickListener(new ActionButtonsClickListener());
    }

    //Click listener for action buttons since they have different behaviour
    class ActionButtonsClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.img_back:
                    String string = txtExpression.getText().toString();
                    if(!string.isEmpty()){
                        txtExpression.setText(string.substring(0,string.length()-1));   //decrease expression by one
                    }
                    txtResult.setText("");
                    break;

                case R.id.btn_clear:
                    txtExpression.setText("");
                    txtResult.setText("");
                    break;

                case R.id.btn_equals:
                    try {
                        Expression expression = new ExpressionBuilder(txtExpression.getText().toString()).build();  //building math expression from string
                        double result = expression.evaluate();  // evaluating the expression
                        txtResult.setText(Double.toString(result));
                    }catch (Exception e){
                        txtExpression.setText("Invalid expression");
                        Log.d("Exception"," message : " + e.getMessage());
                    }

                    break;
            }
        }
    }

    class NumberButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            appendExpression(view,true);  //clicking number button must clear results
        }
    }

    class OperationButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            appendExpression(view,false);
        }
    }

    void appendExpression(View view, boolean canClear){
        Button btn=(Button)view;

        if (txtExpression.getText().toString().equals(AppConstants.MESSAGE_INVALID_EXP))
            txtExpression.setText("");

        if(!txtResult.getText().toString().isEmpty()){
            txtExpression.setText("");
        }

        if (canClear){
            txtResult.setText("");
            txtExpression.setText(txtExpression.getText().toString()+btn.getText().toString());
        }else {
            txtExpression.setText(txtExpression.getText().toString()+txtResult.getText().toString()+btn.getText().toString());
            txtResult.setText("");
        }
    }

}
