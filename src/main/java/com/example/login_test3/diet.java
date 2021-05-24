package com.example.login_test3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class diet extends AppCompatActivity {

    public TextView textView;
    int num;
    DatabaseReference ref;
    private AutoCompleteTextView txtSearch;
    private RecyclerView listData;
    ListView list1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;

    HashMap result = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String email = user.getEmail();
        String uid = user.getUid();
        result.put("email", email);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("userdd");

        Button home = (Button) findViewById(R.id.homebutton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //날짜
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");
        textView.setText(date);
        //

        list1 = (ListView) findViewById(R.id.list1);
        final ArrayList<String> items = new ArrayList<>();
        final ArrayAdapter madapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);
        list1.setAdapter(madapter);
        //목록


        Toast.makeText(diet.this, email, Toast.LENGTH_SHORT).show();

        //저장
        Button save = (Button) findViewById(R.id.save);
        //databaseReference = firebaseDatabase.getReference("UserDiet");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(diet.this, "들어왔는디.", Toast.LENGTH_SHORT).show();

                WriteNewDiet(uid, date, (int) result.get(num), (String) result.get("name"), (String) result.get("type"), (String) result.get("calories"), (String) result.get("standard"));
            }
        });

        //리사이클러
        ref = FirebaseDatabase.getInstance().getReference("dietdata");
        txtSearch = (AutoCompleteTextView) findViewById(R.id.txtSearch);
        listData= (RecyclerView) findViewById(R.id.listData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listData.setLayoutManager(layoutManager);

        dietSearch(items, madapter);
    }

    public class Save{
        public String email;
        public String date;

        public Save(){

        }

        public Save(String email, String date){
            this.email = email;
            this.date =date;

        }

        public String getEmail(){
            return email;
        }

        public String getDate(){
            return date;
        }


        @Override
        public String toString() {
            return "Save{" +
                    "userName='" + date + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    private void dietSearch(ArrayList<String> items, ArrayAdapter madapter) {
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
                          getDiet(selection , items , madapter);

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

    private void getDiet(String selection , ArrayList items, ArrayAdapter adapter) {

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
                        ,ds.child("standard").getValue(String.class), ds.getKey());
                      dietInfos.add(dietInfo);
                    }

                    CustomAdapter customAdapter = new CustomAdapter(dietInfos, items, adapter);
                    listData.setAdapter(customAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);
    }
    static class DietInfo{

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

        public String getKey(){return key;}

        public String name;
        public String type;
        public String calories;
        public String standard;
        public String key;

        public DietInfo(String name, String type, String calories, String standard, String key){
            this.name = name;
            this.type = type;
            this.calories = calories;
            this.standard = standard;
            this.key = key;
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private ArrayList<DietInfo> localDataSet;
      ArrayList<String> items;
        ArrayAdapter madapter;

        public class ViewHolder extends RecyclerView.ViewHolder {
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


        public CustomAdapter(ArrayList<DietInfo> dataSet ,ArrayList<String> items, ArrayAdapter madapter ) {
            this.localDataSet = dataSet;
            this.items = items;
            this.madapter = madapter;

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

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(diet.this,"df", Toast.LENGTH_SHORT).show();
                    // 아이템 추가.

                    int count;
                    count = madapter.getCount();

                    // 아이템 추가.
                    items.add(count,thisdiet.getName()+" 종류:"+ thisdiet.getType()+" 칼로리:"+thisdiet.getCalories()+" 기준:"+thisdiet.getStandard());
                    // listview 갱신

                    getcha(count, thisdiet.getName(), thisdiet.getType(), thisdiet.getCalories(), thisdiet.getStandard());
                    madapter.notifyDataSetChanged();
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

    private void WriteNewDiet(String uid, String date, int num ,String name, String type, String calories, String standard) {

            dietInfo diee = new dietInfo(name, type, calories, standard);
            mDatabase.child(date).child(uid).child(String.valueOf(num)).setValue(diee).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // Write was successful!
                    Toast.makeText(diet.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            Toast.makeText(diet.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });

    }

    public void getcha(int count, String name, String type, String calories, String standard){

         num= count;
            result.put(num, count);
            result.put("name", name);
            result.put("type", type);
            result.put("calories", calories);
            result.put("standard", standard);
    }
}


