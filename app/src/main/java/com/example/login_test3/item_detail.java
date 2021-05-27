package com.example.login_test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class item_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String image = intent.getStringExtra("imageUrl");
//        Log.v("test", "imagelink" +image);

        TextView detail_title = findViewById(R.id.detail_title);
        TextView detail_content = findViewById(R.id.detail_content);
        ImageView detail_image = findViewById(R.id.detail_image);

        Glide.with(this).load(image).into(detail_image);
        detail_title.setText(title);
        detail_content.setText(content);
    }
}