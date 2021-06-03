package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Popup extends AppCompatActivity {
    TextView txtText;
    TextView namText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        namText = (TextView)findViewById(R.id.namText);
        txtText = (TextView)findViewById(R.id.txtText);


        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String que = intent.getStringExtra("name");
        txtText.setText(data);
        namText.setText(que);

    }

    public void mOnClose(View v){

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
