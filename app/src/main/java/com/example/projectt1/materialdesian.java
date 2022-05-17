package com.example.projectt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class materialdesian extends AppCompatActivity {
    Button btn;
    TextView Forgot, createAccount;
    EditText Email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialdesian);
        btn = findViewById(R.id.btn);
        createAccount = findViewById(R.id.Account);
        Forgot = findViewById(R.id.txtForgot);
        Email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!= null){
            startActivity(new Intent(materialdesian.this,activity_dasborad.class));
            finish();
        }
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(materialdesian.this, registration_from.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pass = password.getText().toString();

                if (email.isEmpty()) {
                    Email.setError("Please enter your email");
                } else if (pass.isEmpty()) {
                    password.setError("Please enter your password");
                } else {
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(materialdesian.this, "Registration failed..", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(materialdesian.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(materialdesian.this, activity_dasborad.class));
                            }
                        }
                    });
                }
            }
        });
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(materialdesian.this, forgot_password.class));
            }
        });
    }

}

