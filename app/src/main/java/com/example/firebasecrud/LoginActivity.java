package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText pass,Uname;
    Button loginBtn;
    TextView regV;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass=findViewById(R.id.idEdtPassword);
        Uname=findViewById(R.id.idEdtUserName);
        loginBtn=findViewById(R.id.idBtnLogin);
        regV=findViewById(R.id.idTVNewUser);
        progressBar=findViewById(R.id.idPBLoading);
        mAuth=FirebaseAuth.getInstance();

        regV.setOnClickListener(view -> {
            startActivity(new Intent(this,RegisterActivity.class));
        });
        loginBtn.setOnClickListener(view -> {
            String email=Uname.getText().toString();
            String password=pass.getText().toString();

            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "All fields Required!", Toast.LENGTH_SHORT).show();
            }else {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "login successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Failed to Login!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}