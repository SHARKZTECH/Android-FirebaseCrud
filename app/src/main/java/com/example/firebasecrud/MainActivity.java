package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login);
        reg=findViewById(R.id.reg);

        login.setOnClickListener(view -> {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
        reg.setOnClickListener(view -> {
            Intent intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });
    }
}