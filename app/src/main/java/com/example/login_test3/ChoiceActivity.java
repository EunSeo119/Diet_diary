package com.example.login_test3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class ChoiceActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd; //로그인 입력필드
    private DatabaseReference mDatabase =FirebaseDatabase.getInstance().getReference() ;
    private final ArrayList<String> sport= new ArrayList<>();

    public static Context context_main3;
    public String gCal;
    public int save=0;
    public String gCal2;
    public int save2=0;
    public String gCal3;
    public int save3=0;

    public static Context context_gstrCal;
    public String gstrCal="";
    public String gstrCal2="";
    public String gstrCal3="";



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

        Button button = (Button) findViewById(R.id.mycal);

        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");

        mDatabase  = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("아침");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("점심");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("저녁");

        mDatabase.keepSynced(true);
        mDatabase1.keepSynced(true);
        mDatabase2.keepSynced(true);

        mDatabase.addValueEventListener(new ValueEventListener() {

            int gaga=0;
            String gaga2="";
            String result="";
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

                            result = c.substring(0, c.length()-1);

                            gaga = Integer.parseInt(result);

                            save=save+gaga;

                            Toast.makeText(ChoiceActivity.this,Integer.toString(save), Toast.LENGTH_SHORT).show();

                            gaga2=Integer.toString(save);

                            ((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal = gaga2;
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

            int gaga=0;
            String gaga2="";
            String result="";

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

                        result = c.substring(0, c.length()-1);

                        gaga = Integer.parseInt(result);

                        save2=save2+gaga;

                        Toast.makeText(ChoiceActivity.this,Integer.toString(save2), Toast.LENGTH_SHORT).show();

                        gaga2=Integer.toString(save2);

                        ((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal2 = gaga2;


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });

        mDatabase2.addValueEventListener(new ValueEventListener() {

            int gaga;
            String gaga2;
            String result;

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

                        result = c.substring(0, c.length()-1);

                        gaga = Integer.parseInt(result);

                        save3=save3+gaga;

                        Toast.makeText(ChoiceActivity.this,Integer.toString(save3), Toast.LENGTH_SHORT).show();

                        gaga2=Integer.toString(save3);

                        ((ChoiceActivity)ChoiceActivity.context_gstrCal).gstrCal3 = gaga2;



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

                Intent intent = new Intent(getApplicationContext(), MycheckActivity.class);
                startActivity(intent);

           }
        });

        context_gstrCal = this;
    }
}