package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Notice extends AppCompatActivity {

    TextView note1;
    TextView note2;
    TextView note3;
    String note1_1="2021.05.25\n안녕하세요!\n드디어 칼로리를 자동으로 계산해주는 기능이 업데이트 되었습니다!\n편하게 총 칼로리를 확인하세요";
    String note2_1="2021.05.10\n안녕하세요!\n저희 Diet diary를 써주시는 이용자 여러분 항상 감사합니다.\n식단을 2개 추가할 시에 하나가 빠져서 저장되는 오류가 업데이트 되었습니다." +
            "\n현재는 정상적으로 이용가능 합니다. \n앞으로도 저희 Diet diary는 항상 발전해 나가겠습니다.";
    String note3_1="2021.04.28\n안녕하세요!\n새로운 기능이 추가되었습니다.\n바로 찜 기능입니다!\n그동안 유익한 게시글을 봐도 따로 저장을 해두지 못해 불편하셨을 텐데" +
            "\n이번 업데이트를 통해 새로 생긴 찜기능으로 관심있는 글을 모아 볼 수 있습니다.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        note1 = (TextView) findViewById(R.id.note1);
        note2 = (TextView) findViewById(R.id.note2);
        note3 = (TextView) findViewById(R.id.note3);
        Button mypagebut = (Button) findViewById(R.id.mypagebut);

        mypagebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mypage_a.class);
                startActivity(intent);
            }
        });
    }

    public void mOnPopupClick1(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "칼로리 계산 기능이 업데이트 되었습니다!");
        intent.putExtra("data", note1_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick2(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "식단 추가 오류 업데이트 안내");
        intent.putExtra("data", note2_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick3(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "새로운 기능 안내");
        intent.putExtra("data", note3_1);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                note1.setText(result);
            }
        }
    }




}