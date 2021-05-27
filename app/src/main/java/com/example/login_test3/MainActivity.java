package com.example.login_test3;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    List<Post> post1 = new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calendar = (Button) findViewById(R.id.calendar);
        Button heart = (Button) findViewById(R.id.heart);
        Button upload = (Button) findViewById(R.id.upload);
        Button mypage = (Button) findViewById(R.id.mypage);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        listview = (ListView) findViewById(R.id.listview1);
        Myadapter adapter = new Myadapter(this, R.layout.item_coustom);
        listview.setAdapter(adapter);

        Writingadapter adpater = new Writingadapter(this, R.layout.item_coustom);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), item_detail.class);
                intent.putExtra("title", adapter.getItem(i).title);
                intent.putExtra("content", adapter.getItem(i).content);
                intent.putExtra("imageUrl", adapter.getItem(i).imageUrl);
                intent.putExtra("time", adapter.getItem(i).time);
                intent.putExtra("date", adapter.getItem(i).date);
                intent.putExtra("uid", adapter.getItem(i).uid);
                intent.putExtra("time", adapter.getItem(i).time);
                intent.putExtra("date", adapter.getItem(i).date);

                startActivity(intent);
            }
        });
        database.keepSynced(true);
        database.child("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.child("Post").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Post post = snapshot.getValue(Post.class);
                adapter.insert(post, 0);
                listview.smoothScrollToPosition(adapter.getCount());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), upload.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), calendar.class);
                startActivity(intent);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), Heart.class);
               startActivity(intent);
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mypage_a.class);
                startActivity(intent);
            }
        });
    }
}
