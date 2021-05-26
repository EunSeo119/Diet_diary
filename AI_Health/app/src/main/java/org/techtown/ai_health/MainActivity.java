package org.techtown.ai_health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.listview1);
        Button calendar = (Button) findViewById(R.id.calendar);
        Button heart = (Button) findViewById(R.id.heart);
        Button upload = (Button) findViewById(R.id.upload);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.si)

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
                Intent intent = new Intent(getApplicationContext(), list.class);
                startActivity(intent);
            }
        });
    }
}