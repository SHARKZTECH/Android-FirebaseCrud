package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddActivity extends AppCompatActivity {

    TextInputEditText courseName,coursePrice,courseSuite,courseImgLink,courseLink,courseDescr;
    Button addBtn;
    ProgressBar progressBar;
    FirebaseDatabase fDb;
    DatabaseReference dbRef;
    private String  courseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        courseName=findViewById(R.id.courseName);
        coursePrice=findViewById(R.id.coursePrice);
        courseSuite=findViewById(R.id.courseSuite);
        courseImgLink=findViewById(R.id.courseImage);
        courseLink=findViewById(R.id.courseLink);
        courseDescr=findViewById(R.id.courseDescr);
        addBtn=findViewById(R.id.addBtn);
        progressBar=findViewById(R.id.progressBar);
        fDb=FirebaseDatabase.getInstance();
        dbRef=fDb.getReference("Courses");

        addBtn.setOnClickListener(view -> {
            String name=courseName.getText().toString();
            String price=coursePrice.getText().toString();
            String suite=courseSuite.getText().toString();
            String image=courseImgLink.getText().toString();
            String link=courseLink.getText().toString();
            String description=courseDescr.getText().toString();
            courseId=name;

            if(name.isEmpty()||price.isEmpty()||suite.isEmpty()||image.isEmpty()||link.isEmpty()||description.isEmpty()){
                Toast.makeText(this, "All fields required!", Toast.LENGTH_SHORT).show();
            }else{
                progressBar.setVisibility(View.VISIBLE);
                Courses courses=new Courses(name,description,price,suite,image,link,courseId);
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                          dbRef.child(courseId).setValue(courses);
                          progressBar.setVisibility(View.GONE);
                          Toast.makeText(AddActivity.this, "Course Added!", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(AddActivity.this, "Failed !"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}