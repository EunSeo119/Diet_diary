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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class diet extends AppCompatActivity {

    public TextView textView;

    DatabaseReference ref;
    private AutoCompleteTextView txtSearch;
    private RecyclerView listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

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


