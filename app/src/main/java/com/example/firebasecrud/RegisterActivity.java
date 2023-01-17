package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText Uname,password,password2;
    TextView loginV;
    Button regBtn;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Uname=findViewById(R.id.idEdtUserName);
        password=findViewById(R.id.idEdtPassword);
        password2=findViewById(R.id.idEdtConfirmPassword);
        loginV=findViewById(R.id.idTVLoginUser);
        regBtn=findViewById(R.id.idBtnRegister);
        progressBar=findViewById(R.id.idPBLoading);
        mAuth=FirebaseAuth.getInstance();

        loginV.setOnClickListener(view -> {
            startActivity(new Intent(this,LoginActivity.class));
        });

        regBtn.setOnClickListener(view ->{
            String pass=password.getText().toString();
            String pass2=password2.getText().toString();
            String name=Uname.getText().toString();


            if(!pass.equals(pass2)){
                Toast.makeText(this, "Password don't match!", Toast.LENGTH_SHORT).show();
            }else if (pass.isEmpty()||pass2.isEmpty()||name.isEmpty()){
                Toast.makeText(this, "All fields required!", Toast.LENGTH_SHORT).show();
            }else{
                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(name,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("Task",task.getResult().toString());
                                if(task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegisterActivity.this, "Reg Successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                    finish();
                                }else {
                                    Log.d("Task", "onComplete: "+task.getException());
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegisterActivity.this, "Reg error!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        } );
    }
}