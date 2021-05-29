package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MycheckActivity extends AppCompatActivity {

    public String gbCal;
    public String glCal;
    public String gdCal;
    public int total;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycheck);

        TextView bCal = (TextView)findViewById(R.id.bCal);
        TextView lCal = (TextView)findViewById(R.id.lCal);
        TextView dCal = (TextView)findViewById(R.id.dCal);
        TextView result = (TextView)findViewById(R.id.result);

        TextView Text1 = (TextView) findViewById(R.id.goal);

        gbCal=((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal;
        bCal.setText(gbCal);
        glCal=((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal2;
        lCal.setText(glCal);
        gdCal=((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal3;
        dCal.setText(gdCal);

        total = Integer.parseInt(gbCal) + Integer.parseInt(glCal) + Integer.parseInt(gdCal);

        Button goal = (Button) findViewById(R.id.btn_goal);

        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String TEXT = Text1.getText().toString();

                Toast.makeText(MycheckActivity.this,TEXT, Toast.LENGTH_SHORT).show();

                if((total - Integer.parseInt(TEXT))>0)
                    result.setText("초과");
                else if((total - Integer.parseInt(TEXT))==0)
                    result.setText("적정");
                else if((total - Integer.parseInt(TEXT))<0)
                    result.setText("미만");

            }
        });



    }
}