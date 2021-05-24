package com.example.login_test3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView calories;
    private TextView standard;

    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        name = (TextView) findViewById(R.id.name);
        type = (TextView) findViewById(R.id.type);
        calories = (TextView) findViewById(R.id.calories);
        standard = (TextView) findViewById(R.id.standard);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        ref = FirebaseDatabase.getInstance().getReference("dietdata");
        Query query = ref.orderByKey().equalTo(key);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren())
                    {
                        name.setText(ds.child("name").getValue(String.class));
                        type.setText(ds.child("type").getValue(String.class));
                        calories.setText(ds.child("calories").getValue(String.class));
                        standard.setText(ds.child("standard").getValue(String.class));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);
    }

}