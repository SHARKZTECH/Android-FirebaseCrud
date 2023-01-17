package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase fDb;
    DatabaseReference dbRef;
    MyAdapter myAdapter;
    ArrayList<Courses> coursesList;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar);
        floatingActionButton=findViewById(R.id.addCourse);
        recyclerView=findViewById(R.id.recycler);
        fDb=FirebaseDatabase.getInstance();
        dbRef=fDb.getReference("Courses");
        coursesList=new ArrayList<>();

        floatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(this,AddActivity.class));
        });


        myAdapter=new MyAdapter(coursesList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        progressBar.setVisibility(View.VISIBLE);
        getCourses();

    }
     private void getCourses(){
        coursesList.clear();
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                coursesList.add(snapshot.getValue(Courses.class));
                progressBar.setVisibility(View.GONE);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                progressBar.setVisibility(View.GONE);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                progressBar.setVisibility(View.GONE);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}