package com.example.projectt1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class registration_from extends AppCompatActivity {

    EditText Name, Email, Password, Forgot;
    TextView time, date;
    Button btn;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    MaterialRadioButton Male, Female;
    RadioGroup radioGroup;
    FirebaseAuth mAuth;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_from);
        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.btnRegister);
        time = findViewById(R.id.time);
        date = findViewById(R.id.Date);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Name = findViewById(R.id.edtName);
        Email = findViewById(R.id.edtEmail);
        Password = findViewById(R.id.edtPassword);
        Forgot = findViewById(R.id.edtForgot);
        radioGroup = findViewById(R.id.Gender);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String forgot = Forgot.getText().toString();

                if (name.isEmpty()) {
                    Name.setError(" Please Enter your Name ");
                } else if (email.isEmpty()) {
                    Email.setError("Please Enter your Email");
                } else if (password.isEmpty()) {
                    Password.setError("please Enter your Password");
                } else if (forgot.isEmpty()) {
                    Forgot.setError("Conform Password?");
                } else {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(registration_from.this, MainActivity.class));
                                Toast.makeText(registration_from.this, "Registration Success..", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(registration_from.this, "Registration  Failed..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Male = findViewById(R.id.Male);
                Female = findViewById(R.id.Female);
                if (Male.isChecked()) {
                    Toast.makeText(registration_from.this, Male.getText().toString(), Toast.LENGTH_SHORT).show();
                } else if (Female.isChecked()) {
                    Toast.makeText(registration_from.this, Female.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(registration_from.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int myHour, int myMinutr) {

                        String time = "Time:" + "0" + String.valueOf(myHour) + ":" + String.valueOf(myMinutr);
                        date.setText(time);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(registration_from.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = "Date:" + String.valueOf(day) + "-" + String.valueOf(month + 1)
                                + "-" + String.valueOf(year);
                        time.setText(date);

                    }
                },
                        yy, mm, dd);
                datePickerDialog.show();
            }
        });
    }
}
