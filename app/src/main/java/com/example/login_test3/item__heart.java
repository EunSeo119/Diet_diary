package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class item__heart extends AppCompatActivity {

    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__heart);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String image = intent.getStringExtra("imageUrl");
        String uid = intent.getStringExtra("uid");
        String time = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String getuid = user.getUid();

        Log.v("test", "getuid" +getuid);
        Log.v("test", "uid" +uid);


        TextView detail_title = findViewById(R.id.detail_title);
        TextView detail_content = findViewById(R.id.detail_content);
        ImageView detail_image = findViewById(R.id.detail_image);
        Button detail_remove = findViewById(R.id.detail_remove);
        Button detail_modify = findViewById(R.id.detail_modify);
        Button detail_heart = findViewById(R.id.detail_heart);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("UserHeart").child(getuid).child(date+"_"+time+"_"+getuid);

        Glide.with(this).load(image).into(detail_image);
        detail_title.setText(title);
        detail_content.setText(content);

        detail_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabase.removeValue();
                Intent intent= new Intent(getApplicationContext(), Heart.class);
                startActivity(intent);
            }
        });



    }
}