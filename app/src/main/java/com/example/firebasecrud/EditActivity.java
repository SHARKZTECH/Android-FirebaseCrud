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

import java.util.HashMap;

public class EditActivity extends AppCompatActivity {
    TextInputEditText courseName,coursePrice,courseSuite,courseImgLink,courseLink,courseDescr;
    Button updateBtn;
    ProgressBar progressBar;
    FirebaseDatabase fDb;
    DatabaseReference dbRef;
    private String  courseId;
    Courses courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        courseName=findViewById(R.id.courseName);
        coursePrice=findViewById(R.id.coursePrice);
        courseSuite=findViewById(R.id.courseSuite);
        courseImgLink=findViewById(R.id.courseImage);
        courseLink=findViewById(R.id.courseLink);
        courseDescr=findViewById(R.id.courseDescr);
        updateBtn=findViewById(R.id.updateBtn);
        progressBar=findViewById(R.id.progressBar);
        fDb=FirebaseDatabase.getInstance();

        courses=getIntent().getParcelableExtra("course");
        if(courses != null){
            courseName.setText(courses.getName());
            coursePrice.setText(courses.getPrice());
            courseSuite.setText(courses.getSuite());
            courseImgLink.setText(courses.getImage());
            courseLink.setText(courses.getLink());
            courseDescr.setText(courses.getDescription());
            courseId=courses.getId();
        }
        dbRef=fDb.getReference("Courses").child(courseId);


        updateBtn.setOnClickListener(view -> {
            String name=courseName.getText().toString();
            String price=coursePrice.getText().toString();
            String suite=courseSuite.getText().toString();
            String image=courseImgLink.getText().toString();
            String link=courseLink.getText().toString();
            String description=courseDescr.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            HashMap<String,Object> map=new HashMap<>();
            map.put("name",name);
            map.put("description",description);
            map.put("price",price);
            map.put("suite",suite);
            map.put("image",image);
            map.put("link",link);
            map.put("id",courseId);

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    progressBar.setVisibility(View.GONE);
                    dbRef.updateChildren(map);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(EditActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(EditActivity.this, "Failed"+error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}