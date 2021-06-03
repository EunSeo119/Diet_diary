package com.example.login_test3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class upload extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Uri filePath;
    public String downloadUrl;
    private ImageView preview;
    String filename;
    public static Context context_main;
    public String name;

    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();



        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        EditText title = (EditText) findViewById(R.id.title);
        EditText content = (EditText) findViewById(R.id.content);
        Button write = (Button) findViewById(R.id.write);
        Button camera = (Button) findViewById(R.id.camera);
        preview = (ImageView) findViewById(R.id.iv_preview);
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("eunsseo").child("UserAccount").child(uid);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String n = snapshot.child("name").getValue(String.class);
//                Toast.makeText(upload.this,name, Toast.LENGTH_SHORT).show();
                name = n;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;



        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                SimpleDateFormat getDate = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat getTime = new SimpleDateFormat("hh:mm:ss");
                downloadUrl = "https://firebasestorage.googleapis.com/v0/b/ai-hometraining.appspot.com/o/images%2F" + filename +"?alt=media";


                String sdate = getDate.format(mDate);
                String stime = getTime.format(mDate);
                String stitle = title.getText().toString();
                String scontent = content.getText().toString();
                String sImageUrl = downloadUrl;
                String sUid = uid;
                String snickname = name;
                Toast.makeText(upload.this,name+"이 들어왔습니다.", Toast.LENGTH_SHORT).show();

                Post post = new Post(stitle, scontent, sdate, stime, sImageUrl,snickname, sUid);
                PostUser postuser = new PostUser(stitle, scontent, sdate, stime, sImageUrl);

                database.child("Post/"+sdate+"_"+stime+"_"+uid).setValue(post);
                database.child("PostUser").child(uid+"/"+sdate+"_"+stime).setValue(postuser);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            filePath = data.getData();
            Log.d(TAG, "uri:" + String.valueOf(filePath));
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                preview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadFile() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            FirebaseStorage storage = FirebaseStorage.getInstance();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMhh_mmss");
            Date now = new Date();
            filename = formatter.format(now) + ".png";

            StorageReference storageRef = storage.getReferenceFromUrl("gs://ai-hometraining.appspot.com").child("images/" + filename);

            storageRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests")
                            double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}

class PostUser extends Post {

    String title;
    String content;
    String date;
    String time;
    String imageUrl;


    PostUser(){}

    PostUser(String title, String content, String date, String time, String imageUrl){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageUrl = imageUrl;
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

    public String getImageUrl(){
        return imageUrl;
    }
    public void SetImageUrl(String standard){
        this.imageUrl= standard;
    }

}

class Post {

    String title;
    String content;
    String date;
    String time;
    String imageUrl;
    String uid;
    String name;

    Post(){}

    Post(String title, String content, String date, String time, String imageUrl, String name, String uid){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageUrl = imageUrl;
        this.name = name;
        this.uid = uid;

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

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void SetImageUrl(String standard){
        this.imageUrl= standard;
    }
    public String getuid() {return uid;}
    public void Setuid(String uid){this.uid=uid;}

}

class PostHeart {

    String title;
    String content;
    String date;
    String time;
    String imageUrl;
    String uid;

    PostHeart(){}

    PostHeart(String title, String content, String date, String time, String imageUrl, String uid){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageUrl = imageUrl;
        this.uid = uid;
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

    public String getImageUrl(){
        return imageUrl;
    }
    public void SetImageUrl(String standard){
        this.imageUrl= standard;
    }
    public String getuid() {return uid;}
    public void Setuid(String uid){this.uid=uid;}

}
