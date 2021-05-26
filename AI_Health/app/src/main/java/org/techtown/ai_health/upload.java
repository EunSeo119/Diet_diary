package org.techtown.ai_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        EditText title = (EditText) findViewById(R.id.title);
        EditText content = (EditText) findViewById(R.id.content);
        Button write = (Button) findViewById(R.id.write);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat getDate = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat getTime = new SimpleDateFormat("hh:mm:ss");

                String sdate = getDate.format(mDate);
                String stime = getTime.format(mDate);
                String stitle = title.getText().toString();
                String scontent = content.getText().toString();
                Post post = new Post(stitle, scontent, sdate, stime);
                database.child("Post").push().setValue(post);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

class Post {

    String title;
    String content;
    String date;
    String time;
    String image;

    Post(){}

    Post(String title, String content, String date, String time){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String name){
        this.title = name;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date= date;
    }
    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time= time;
    }


}
