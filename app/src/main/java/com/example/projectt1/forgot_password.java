package com.example.projectt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
Button Forgot;
EditText txtEmail;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Forgot=findViewById(R.id.forgotbtn);
        txtEmail=findViewById(R.id.Email);
        mAuth = FirebaseAuth.getInstance();
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=txtEmail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(forgot_password.this, "Send Reset Link", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(forgot_password.this, "Reset Link Sent Failed..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            });
        };
    }
