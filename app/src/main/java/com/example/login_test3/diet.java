package com.example.login_test3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class diet extends AppCompatActivity {

    public TextView textView;
    public int Btime=0;
    public int Ltime=0;
    public int Dtime=0;
    public Button checkD;


    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스

//    private FirebaseDatabase mfirebaseDatabase = FirebaseDatabase.getInstance();

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref;
    private AutoCompleteTextView txtSearch;
    private RecyclerView listData;
    public String gCalories, gName, gStandard, gType;
    private DatabaseReference mDatabase1= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mDatabase2= FirebaseDatabase.getInstance().getReference();


    HashMap result = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        String email = user.getEmail();
        result.put("email", email);

        //날짜
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");
        textView.setText(date);

        mDatabase  = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("아침");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("점심");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("UserDiet").child(date).child("저녁");


        Button home = (Button) findViewById(R.id.homebutton);
        Button btn_save = findViewById(R.id.btn_save);
        Button btn_save2 = findViewById(R.id.btn_save2);
        Button btn_save3 = findViewById(R.id.btn_save3);
        Button returnback = (Button) findViewById(R.id.returnback);

        returnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), calendar.class);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 접속한 회원 정보 불러오기기기기긱ㄱㄱ기\
//                String gVar = ((LoginActivity)LoginActivity.context_main).gVar;
//                String result = gVar.substring(0, gVar.length()-10);
//                //               gVar.substring(1, 3);
//                Toast.makeText(diet.this, result, Toast.LENGTH_SHORT).show();

                //              databaseReference.child("UserDiet").push().setValue(gVar);


                mDatabase.child(uid).child(""+Btime).child("calories").setValue(gCalories);
                mDatabase.child(uid).child(""+Btime).child("name").setValue(gName);
                mDatabase.child(uid).child(""+Btime).child("standard").setValue(gStandard);
                mDatabase.child(uid).child(""+Btime).child("type").setValue(gType);
                /* mDatabase.child("UserDiet").child(result).child("calories"+time).setValue(gCalories);
                mDatabase.child("UserDiet").child(result).child("name"+time).setValue(gName);
                mDatabase.child("UserDiet").child(result).child("standard"+time).setValue(gStandard);
                mDatabase.child("UserDiet").child(result).child("type"+time).setValue(gType);*/
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.listData);

                Btime+=1;

                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(diet.this);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);





            }
        });

        btn_save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 접속한 회원 정보 불러오기기기기긱ㄱㄱ기\
//                String gVar = ((LoginActivity)LoginActivity.context_main).gVar;
//                String result = gVar.substring(0, gVar.length()-10);
                //               gVar.substring(1, 3);
//                Toast.makeText(diet.this, result, Toast.LENGTH_SHORT).show();

                //              databaseReference.child("UserDiet").push().setValue(gVar);

                mDatabase1.child(uid).child(""+Ltime).child("calories").setValue(gCalories);
                mDatabase1.child(uid).child(""+Ltime).child("name").setValue(gName);
                mDatabase1.child(uid).child(""+Ltime).child("standard").setValue(gStandard);
                mDatabase1.child(uid).child(""+Ltime).child("type").setValue(gType);
                /* mDatabase.child("UserDiet").child(result).child("calories"+time).setValue(gCalories);
                mDatabase.child("UserDiet").child(result).child("name"+time).setValue(gName);
                mDatabase.child("UserDiet").child(result).child("standard"+time).setValue(gStandard);
                mDatabase.child("UserDiet").child(result).child("type"+time).setValue(gType);*/
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.listData);

                Ltime+=1;

                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(diet.this);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);





            }
        });

        btn_save3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 접속한 회원 정보 불러오기기기기긱ㄱㄱ기

                mDatabase2.child(uid).child(""+Dtime).child("calories").setValue(gCalories);
                mDatabase2.child(uid).child(""+Dtime).child("name").setValue(gName);
                mDatabase2.child(uid).child(""+Dtime).child("standard").setValue(gStandard);
                mDatabase2.child(uid).child(""+Dtime).child("type").setValue(gType);
                /* mDatabase.child("UserDiet").child(result).child("calories"+time).setValue(gCalories);
                mDatabase.child("UserDiet").child(result).child("name"+time).setValue(gName);
                mDatabase.child("UserDiet").child(result).child("standard"+time).setValue(gStandard);
                mDatabase.child("UserDiet").child(result).child("type"+time).setValue(gType);*/
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.listData);

                Dtime+=1;

                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(diet.this);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);





            }
        });

        checkD = findViewById(R.id.checkD);


        checkD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ChoiceActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });




        //

        ref = FirebaseDatabase.getInstance().getReference("dietdata");
        txtSearch = (AutoCompleteTextView) findViewById(R.id.txtSearch);
        listData= (RecyclerView) findViewById(R.id.listData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listData.setLayoutManager(layoutManager);

        dietSearch();
    }

    private void dietSearch() {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<String> names = new ArrayList<>();

                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        String n=ds.child("name").getValue(String.class);

                        names.add(n);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
                    txtSearch.setAdapter(adapter);

                    txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selection = parent.getItemAtPosition(position).toString();
                            getDiet(selection);
                        }

                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);
    }
//자동완성 기능

    private void getDiet(String selection) {

        Query query = ref.orderByChild("name").equalTo(selection);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<DietInfo> dietInfos = new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        DietInfo dietInfo = new DietInfo(ds.child("name").getValue(String.class),
                                ds.child("type").getValue(String.class), ds.child("calories").getValue(String.class)
                                ,ds.child("standard").getValue(String.class));
                        dietInfos.add(dietInfo);


                        gCalories=ds.child("calories").getValue(String.class);
                        gName=ds.child("name").getValue(String.class);
                        gStandard=ds.child("standard").getValue(String.class);
                        gType=ds.child("type").getValue(String.class);

 /*                     Toast.makeText(diet.this, gCalories, Toast.LENGTH_SHORT).show();
                        Toast.makeText(diet.this, ds.child("standard").getValue(String.class), Toast.LENGTH_SHORT).show();*/


                    }

                    CustomAdapter customAdapter = new CustomAdapter(dietInfos);
                    listData.setAdapter(customAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);
    }
    class DietInfo{

        public String getName(){
            return name;
        }

        public String getType(){
            return type;
        }

        public String getCalories(){
            return calories;
        }

        public String getStandard(){
            return standard;
        }

        public String name;
        public String type;
        public String calories;
        public String standard;

        public DietInfo(String name, String type, String calories, String standard){
            this.name = name;
            this.type = type;
            this.calories = calories;
            this.standard = standard;
        }
    }

    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private ArrayList<DietInfo> localDataSet;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView type;
            TextView calories;
            TextView standard;


            public ViewHolder(View v) {
                super(v);

                name = (TextView) v.findViewById(R.id.name);
                type = (TextView) v.findViewById(R.id.type);
                calories  = (TextView) v.findViewById(R.id.calories);
                standard = (TextView) v.findViewById(R.id.standard);
            }

        }


        public CustomAdapter(ArrayList<DietInfo> dataSet) {
            this.localDataSet = dataSet;
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
            View view= layoutInflater.inflate(R.layout.row_style, parent, false);
            return new ViewHolder(view);
        }


        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            DietInfo thisdiet = localDataSet.get(position);

            holder.name.setText(thisdiet.getName());
            holder.type.setText(thisdiet.getType());
            holder.calories.setText(thisdiet.getCalories());
            holder.standard.setText(thisdiet.getStandard());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }





}


