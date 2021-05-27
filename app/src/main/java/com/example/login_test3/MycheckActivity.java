package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



public class MycheckActivity extends AppCompatActivity {

    public String gbCal;
    public String glCal2;
    public String gdCal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycheck);

        TextView bCal = (TextView)findViewById(R.id.bCal);

        gbCal=((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal;
        bCal.setText(gbCal);
    }
}