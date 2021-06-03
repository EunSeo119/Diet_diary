package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class item_modify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_modify);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String uid = intent.getStringExtra("uid");
        String image = intent.getStringExtra("imageUrl");
        String time = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");

        EditText modify_title = findViewById(R.id.modify_title);
        EditText modify_content = findViewById(R.id.modify_content);
        ImageView modify_image = findViewById(R.id.modify_image);
        Button modify_write = findViewById(R.id.modify_write);

        modify_title.setText(title);
        modify_content.setText(content);
        Glide.with(this).load(image).into(modify_image);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        modify_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long now = System.currentTimeMillis();
//                Date mDate = new Date(now);
//
//                SimpleDateFormat getDate = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat getTime = new SimpleDateFormat("hh:mm:ss");
//
//                String sdate = getDate.format(mDate);
//                String stime = getTime.format(mDate);
                String stitle = modify_title.getText().toString();
                String scontent = modify_content.getText().toString();
                String sImageUrl = image;

                Post post = new Post(stitle, scontent, date, time, sImageUrl, uid);
                PostUser postuser = new PostUser(stitle, scontent, date, time, sImageUrl);

                mDatabase.getReference().child("Post/"+date+"_"+time+"_"+uid).setValue(post);
                mDatabase.getReference().child("PostUser").child(uid+"/"+date+"_"+time).setValue(postuser);
//                Intent intent= new Intent(getApplicationContext(), item_detail.class);
//                intent.putExtra("title", title);
//                intent.putExtra("content", content);
//                intent.putExtra("imageUrl", image);
//                intent.putExtra("time", time);
//                intent.putExtra("date", date);
//                intent.putExtra("uid", uid);
//                intent.putExtra("time", time);
//                intent.putExtra("date", date);
//
//                startActivity(intent);
            }
        });


    }
}