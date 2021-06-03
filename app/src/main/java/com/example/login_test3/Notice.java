package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Notice extends AppCompatActivity {

    TextView note1;
    TextView note2;
    TextView note3;
    String note1_1="2021.05.25\n안녕하세요!\n드디어 칼로리를 자동으로 계산해주는 기능이 업데이트 되었습니다!\n 편하게 총 칼로리를 확인하세요";
    String note2_1="2021.05.10\n오류가 업데이트 업데이트 되었습니다.";
    String note3_1="2021.04.28\n기능이 추가되었습니다.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        note1 = (TextView) findViewById(R.id.note1);
        note2 = (TextView) findViewById(R.id.note2);
        note3 = (TextView) findViewById(R.id.note3);


    }

    public void mOnPopupClick1(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("data", note1_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick2(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("data", note2_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick3(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("data", note3_1);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                note1.setText(result);
            }
        }
    }




}