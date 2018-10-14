package com.thetasolutions.calculator.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.thetasolutions.calculator.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnOpenCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btnOpenCal=findViewById(R.id.btn_OpenCalculator);
//        btnOpenCal.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        // opens next activity
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
