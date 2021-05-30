package com.example.login_test3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Heart extends AppCompatActivity {

    public String time;
    public String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);

        ListView listview = findViewById(R.id.listview);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String getuid = user.getUid();

        Myadapter adapter = new Myadapter(this, R.layout.item_coustom);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), item__heart.class);
                intent.putExtra("title", adapter.getItem(i).title);
                intent.putExtra("content", adapter.getItem(i).content);
                intent.putExtra("imageUrl", adapter.getItem(i).imageUrl);
                intent.putExtra("time", adapter.getItem(i).time);
                intent.putExtra("date", adapter.getItem(i).date);
                intent.putExtra("uid", adapter.getItem(i).uid);

                date = adapter.getItem(i).date;
                time = adapter.getItem(i).time;

                startActivity(intent);
            }
        });


        database.child("UserHeart").child(getuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Post postheart = snapshot.getValue(Post.class);
                adapter.insert(postheart, 0);
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

    }
}