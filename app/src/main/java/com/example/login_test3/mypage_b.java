package com.example.login_test3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class mypage_b extends AppCompatActivity {


    TextView email;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_b);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        TextView writing = (TextView) findViewById(R.id.textview1);
        Button logout = (Button) findViewById(R.id.join_button);
        TextView notice = (TextView) findViewById(R.id.textView3);
        TextView fna = (TextView) findViewById(R.id.textview2);
        Button calender = (Button) findViewById(R.id.calender) ;
        email = (TextView) findViewById(R.id.membername);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("eunsseo").child("UserAccount").child(uid);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String n = snapshot.child("name").getValue(String.class);
                name = n;
                email.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;
//        Toast.makeText(mypage_b.this, name+"제발좀 돼라~", Toast.LENGTH_SHORT).show();
//        String realName = name;
//        email = (TextView) findViewById(R.id.membername);
//        email.setText(realName);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), calendar.class);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(getApplicationContext(), mypage_a.class);
                startActivity(intent);
            }
        });

        fna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FnA.class);
                startActivity(intent);
            }
        });

        writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), my_writing.class);
                startActivity(intent);
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notice.class);
                startActivity(intent);
            }
        });

        Button home = (Button) findViewById(R.id.homebutton);
        Button calendar = (Button) findViewById(R.id.calendar);
        Button heart = (Button) findViewById(R.id.heart);
        Button mypage = (Button) findViewById(R.id.mypage);

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Heart.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}