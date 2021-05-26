package com.example.login_test3;

import android.content.Context;
import android.content.Intent;
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
    private DatabaseReference mDatabase =FirebaseDatabase.getInstance().getReference() ;
    private final ArrayList<String> sport= new ArrayList<>();

    public static Context context_main;
    public String gCal;
    public int save=0;
    public String gCal2;
    public int save2=0;
    public String gCal3;
    public int save3=0;



    public Button button;
    public int cnt = 0;

    private DatabaseReference mDatabase1= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mDatabase2= FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);




        TextView breakfast1 = (TextView)findViewById(R.id.breakfast1);
        TextView breakfast2 = (TextView)findViewById(R.id.breakfast2);
        TextView breakfast3 = (TextView)findViewById(R.id.breakfast3);
        TextView lunch1 = (TextView)findViewById(R.id.launch1);
        TextView lunch2 = (TextView)findViewById(R.id.launch2);
        TextView lunch3 = (TextView)findViewById(R.id.launch3);
        TextView dinner1 = (TextView)findViewById(R.id.dinner1);
        TextView dinner2 = (TextView)findViewById(R.id.dinner2);
        TextView dinner3 = (TextView)findViewById(R.id.dinner3);

//        textview.setText("대한민국");

        /*mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UserDiet");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


       /* TextView email_info;
​
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // 로그인한 유저의 정보 가져오기
        String uid = user != null ? user.getUid() : null; // 로그인한 유저의 고유 uid 가져오기
​
        mFirebaseAuth = FirebaseAuth.getInstance(); // 유저 계정 정보 가져오기
        mDatabase = FirebaseDatabase.getInstance().getReference(); // 파이어베이스 realtime database 에서 정보 가져오기
        DatabaseReference email = mDatabase.child("UserDiet").child("2021");    // 이메일*/

//        String gVar = ((LoginActivity)LoginActivity.context_main).gVar;
//        String result = gVar.substring(0, gVar.length()-10);
//        final int[] record = {1};
//        final int[] record2 = {1};
//        final int[] record3 = {1};

        //파이어베이스에서 데이타를 읽어올 경로
//        mDatabaseRef= FirebaseDatabase.getInstance().getReference("UserDiet2").child("2021").child("5").child("26").child(result).child("아침");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String firemessage = snapshot.getValue().toString();    //문자열로 받기
//                    sport.add(firemessage);  //리스트에 추가
//                    if(record[0] ==1){
//                        breakfast1.setText(firemessage);
//                        record[0] +=1;
//                    }
//                    else if(record[0] ==2){
//                        breakfast2.setText(firemessage);
//                        record[0] +=1;
//                    }
//                    else if(record[0] ==3){
//                        breakfast3.setText(firemessage);
//                        record[0] +=1;
//                    }
//
//                    Toast.makeText(ChoiceActivity.this,firemessage, Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//            }
//        });
//
//
//        mDatabaseRef= FirebaseDatabase.getInstance().getReference("UserDiet2").child("2021").child("5").child("26").child(result).child("점심");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String firemessage = snapshot.getValue().toString();    //문자열로 받기
//                    sport.add(firemessage);  //리스트에 추가
//                    if(record2[0] ==1){
//                        launch1.setText(firemessage);
//                        record2[0] +=1;
//                    }
//                    else if(record2[0] ==2){
//                        launch2.setText(firemessage);
//                        record2[0] +=1;
//                    }
//                    else if(record2[0] ==3){
//                        launch3.setText(firemessage);
//                        record2[0] +=1;
//                    }
//
//                    Toast.makeText(ChoiceActivity.this,firemessage, Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//            }
//        });
//
//        mDatabaseRef= FirebaseDatabase.getInstance().getReference("UserDiet2").child("2021").child("5").child("26").child(result).child("저녁");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String firemessage = snapshot.getValue().toString();    //문자열로 받기
//                    sport.add(firemessage);  //리스트에 추가
//                    if(record3[0] ==1){
//                        dinner1.setText(firemessage);
//                        record3[0] +=1;
//                    }
//                    else if(record3[0] ==2){
//                        dinner2.setText(firemessage);
//                        record3[0] +=1;
//                    }
//                    else if(record3[0] ==3){
//                        dinner3.setText(firemessage);
//                        record3[0] +=1;
//                    }
//
//                    Toast.makeText(ChoiceActivity.this,firemessage, Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//            }
//        });
        Button button = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");

        mDatabase  = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("아침");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("점심");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("저녁");

        mDatabase.keepSynced(true);
        mDatabase1.keepSynced(true);
        mDatabase2.keepSynced(true);

        mDatabase.addValueEventListener(new ValueEventListener() {

            int gaga;
            String gaga2;
            String result;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    for (DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        for(int i =0;i<3;i++) {

                            String n = ds.child(""+i).child("name").getValue(String.class);
                            String c = ds.child(""+i).child("calories").getValue(String.class);
                            String t = ds.child(""+i).child("type").getValue(String.class);
                            String s = ds.child(""+i).child("standard").getValue(String.class);

                            //   breakfast1.setText(n + "/" + c + "/" + t + "/" + s);

                            if(i== 0)
                            { breakfast1.setText(n + "/" + c + "/" + t + "/" + s);
                            }
                            else if (i ==1 )
                            {
                                breakfast2.setText(n + "/" + c + "/" + t + "/" + s);
                            }
                            else if(i == 2)
                            {
                                breakfast3.setText(n + "/" + c + "/" + t + "/" + s);

                            }

                            result = c.substring(0,c.length()-1);

                            gaga = Integer.parseInt(result);

                            save=save+gaga;
                            gaga2=Integer.toString(save);

                            Toast.makeText(ChoiceActivity.this,gaga2, Toast.LENGTH_SHORT).show();

                            //((ChoiceActivity)ChoiceActivity.context_main).gCal=gaga2;
                        }
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });

        mDatabase1.addValueEventListener(new ValueEventListener() {
            int strCal2;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for(int i =0;i<3;i++) {

                        String n = ds.child(""+i).child("name").getValue(String.class);
                        String c = ds.child(""+i).child("calories").getValue(String.class);
                        String t = ds.child(""+i).child("type").getValue(String.class);
                        String s = ds.child(""+i).child("standard").getValue(String.class);
                        //       lunch1.setText(n + "/" + c + "/" + t + "/" + s);
                        if(i== 0)
                        { lunch1.setText(n + "/" + c + "/" + t + "/" + s);
                        }
                        else if (i ==1 )
                        {
                            lunch2.setText(n + "/" + c + "/" + t + "/" + s);
                        }
                        else if(i == 2)
                        {
                            lunch3.setText(n + "/" + c + "/" + t + "/" + s);
                        }


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });

        mDatabase2.addValueEventListener(new ValueEventListener() {
            int strCal3;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    for(int i =0;i<3;i++) {
                        String n = ds.child(""+i).child("name").getValue(String.class);
                        String c = ds.child(""+i).child("calories").getValue(String.class);
                        String t = ds.child(""+i).child("type").getValue(String.class);
                        String s = ds.child(""+i).child("standard").getValue(String.class);
                        // dinner1.setText(n + "/" + c + "/" + t + "/" + s);

                        if(i== 0)
                        { dinner1.setText(n + "/" + c + "/" + t + "/" + s);
                        }
                        else if (i ==1 )
                        {
                            dinner2.setText(n + "/" + c + "/" + t + "/" + s);
                        }
                        else if(i == 2)
                        {
                            dinner3.setText(n + "/" + c + "/" + t + "/" + s);
                        }


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        if(dataSnapshot.exists())
//                        {
//                            for (DataSnapshot ds : dataSnapshot.getChildren())
//                            {
//                                for(int i =0;i<3;i++) {
//
//                                    String n = ds.child(""+i).child("name").getValue(String.class);
//                                    String c = ds.child(""+i).child("calories").getValue(String.class);
//                                    String t = ds.child(""+i).child("type").getValue(String.class);
//                                    String s = ds.child(""+i).child("standard").getValue(String.class);
//
//                             //   breakfast1.setText(n + "/" + c + "/" + t + "/" + s);
//
//                                    if(i== 0)
//                                    { breakfast1.setText(n + "/" + c + "/" + t + "/" + s);
//                                    }
//                                    else if (i ==1 )
//                                    {
//                                        breakfast2.setText(n + "/" + c + "/" + t + "/" + s);
//                                    }
//                                    else if(i == 2)
//                                    {
//                                        breakfast3.setText(n + "/" + c + "/" + t + "/" + s);
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        // Getting Post failed, log a message
//                    }
//                });
//
//                mDatabase1.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            for(int i =0;i<3;i++) {
//
//                                String n = ds.child(""+i).child("name").getValue(String.class);
//                                String c = ds.child(""+i).child("calories").getValue(String.class);
//                                String t = ds.child(""+i).child("type").getValue(String.class);
//                                String s = ds.child(""+i).child("standard").getValue(String.class);
//                     //       lunch1.setText(n + "/" + c + "/" + t + "/" + s);
//                                if(i== 0)
//                                { lunch1.setText(n + "/" + c + "/" + t + "/" + s);
//                                }
//                                else if (i ==1 )
//                                {
//                                    lunch2.setText(n + "/" + c + "/" + t + "/" + s);
//                                }
//                                else if(i == 2)
//                                {
//                                    lunch3.setText(n + "/" + c + "/" + t + "/" + s);
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        // Getting Post failed, log a message
//                    }
//                });
//
//                mDatabase2.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//
//                            for(int i =0;i<3;i++) {
//                                String n = ds.child(""+i).child("name").getValue(String.class);
//                                String c = ds.child(""+i).child("calories").getValue(String.class);
//                                String t = ds.child(""+i).child("type").getValue(String.class);
//                                String s = ds.child(""+i).child("standard").getValue(String.class);
//                           // dinner1.setText(n + "/" + c + "/" + t + "/" + s);
//
//                                if(i== 0)
//                                { dinner1.setText(n + "/" + c + "/" + t + "/" + s);
//                                }
//                                else if (i ==1 )
//                                {
//                                    dinner2.setText(n + "/" + c + "/" + t + "/" + s);
//                                }
//                                else if(i == 2)
//                                {
//                                    dinner3.setText(n + "/" + c + "/" + t + "/" + s);
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        // Getting Post failed, log a message
//                    }
//                });

           }
        });


    }
}