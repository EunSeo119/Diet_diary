package com.example.login_test3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChoiceActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd; //로그인 입력필드
    private DatabaseReference mDatabase ;
    private final ArrayList<String> sport= new ArrayList<>();

    public static Context context_main;
    public String gVar;
    public Button button;
    public int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        TextView breakfast1 = (TextView)findViewById(R.id.breakfast1);
        TextView breakfast2 = (TextView)findViewById(R.id.breakfast2);
        TextView breakfast3 = (TextView)findViewById(R.id.breakfast3);

//        textview.setText("대한민국");

        /*mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UserDiet");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);*/




       /* TextView email_info;
​
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // 로그인한 유저의 정보 가져오기
        String uid = user != null ? user.getUid() : null; // 로그인한 유저의 고유 uid 가져오기
​
        mFirebaseAuth = FirebaseAuth.getInstance(); // 유저 계정 정보 가져오기
        mDatabase = FirebaseDatabase.getInstance().getReference(); // 파이어베이스 realtime database 에서 정보 가져오기
        DatabaseReference email = mDatabase.child("UserDiet").child("2021");    // 이메일*/

        String gVar = ((LoginActivity)LoginActivity.context_main).gVar;
        String result = gVar.substring(0, gVar.length()-10);
        final int[] record = {1};

        //파이어베이스에서 데이타를 읽어올 경로
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("UserDiet2").child("2021").child("5").child("26").child(result);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String firemessage = snapshot.getValue().toString();    //문자열로 받기
                    sport.add(firemessage);  //리스트에 추가
                    if(record[0] ==1){
                        breakfast1.setText(firemessage);
                        record[0] +=1;
                    }
                    else if(record[0] ==2){
                        breakfast2.setText(firemessage);
                        record[0] +=1;
                    }
                    else if(record[0] ==3){
                        breakfast3.setText(firemessage);
                        record[0] +=1;
                    }

                    Toast.makeText(ChoiceActivity.this,firemessage, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });
    }
}