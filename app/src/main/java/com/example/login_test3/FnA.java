package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FnA extends AppCompatActivity {
    TextView fna1;
    TextView fna2;
    TextView fna3;
    TextView fna4;
    TextView fna5;
    String fna1_1="회원가입은 첫페이지에서 로그인을 누른 뒤\n회원가입을 누르면 됩니다.\n ";
    String fna2_1="식단은 아래 하단 메뉴 바에서 달력 모양을 선택한 뒤\n원하는 날짜를 선택해 식단을 추가하실 수 있습니다.\n그 외에 칼로리 확인, 식단확인을 할 수 있습니다.";
    String fna3_1="게시판에 식단을 올리는 방법은\n하단 메뉴 바에서 집 모양을 누른 뒤\n" +
            "글쓰기를 선택하시고 글올리기 버튼을 누르면 성공!\n글을 올리실때 제목과 내용, 사진까지 올리실 수 있습니다.\n";
    String fna4_1="게시글을 잘 못 올렸을 경우\n마이페이지에 들어가시면 삭제를 할 수 있습니다.\n삭제뿐만아니라 수정도 가능합니다!";
    String fna5_1="총칼로리의 경우는\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fna);

        fna1 = (TextView) findViewById(R.id.fna1);
        fna2 = (TextView) findViewById(R.id.fna2);
        fna3 = (TextView) findViewById(R.id.fna3);
        fna4 = (TextView) findViewById(R.id.fna4);
        fna5 = (TextView) findViewById(R.id.fna5);
        Button mypagebut = (Button) findViewById(R.id.mypagebut);

        mypagebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mypage_a.class);
                startActivity(intent);
            }
        });

    }

    public void mOnPopupClick4(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "회원가입을 어떻게 하나요?");
        intent.putExtra("data", fna1_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick5(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "식단을 어떻게 등록하나요?");
        intent.putExtra("data", fna2_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick6(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "게시글을 어떻게 올리나요?");
        intent.putExtra("data", fna3_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick7(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "게시글을 잘못 올렸어요 삭제할 수 있나요?");
        intent.putExtra("data", fna4_1);
        startActivityForResult(intent, 1);
    }

    public void mOnPopupClick8(View v){
        Intent intent = new Intent(this, Popup.class);
        intent.putExtra("name", "총 칼로리는 어디서 확인할 수 있나요?");
        intent.putExtra("data", fna5_1);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                fna1.setText(result);
            }
        }
    }
}